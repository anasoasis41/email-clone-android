package com.example.emailcloneapp.ui.home.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.emailcloneapp.EMAIL_WEB_SERVICE_URL
import com.example.emailcloneapp.utils.DatabseConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class EmailDataRepository(val app: Application) {
    val emailData = MutableLiveData<List<EmailData>>()
    private val emailDao = DatabseConfig.getDatabase(app).emailDao()

    init {
        // Background Thread
        CoroutineScope(Dispatchers.IO).launch {
            // Get all the data from database
            val data = emailDao.getAllEmails()
            if (data.isEmpty()) {
                callWebService()
            } else {
                emailData.postValue(data)
                // Foreground Thread
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            withContext(Dispatchers.Main) {
                Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
            }
            Timber.i( "Calling web service")
            val retrofit = Retrofit.Builder()
                .baseUrl(EMAIL_WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(EmailService::class.java)
            val serviceData = service.getEmailData().body() ?: emptyList()
            emailData.postValue(serviceData)
            // Save data in room database
            emailDao.deleteAllEmails()
            emailDao.insertEmails(serviceData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}
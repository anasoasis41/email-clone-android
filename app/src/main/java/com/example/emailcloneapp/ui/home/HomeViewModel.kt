package com.example.emailcloneapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.emailcloneapp.ui.home.data.EmailData
import com.example.emailcloneapp.ui.home.data.EmailDataRepository

class HomeViewModel(val app: Application) : AndroidViewModel(app) {

    private val dataRepo = EmailDataRepository(app)
    val emailData = dataRepo.emailData

    val selectedEmail = MutableLiveData<EmailData>()

    fun loadEmailData() {
        dataRepo.refreshEmailDataFromWeb()
    }
}
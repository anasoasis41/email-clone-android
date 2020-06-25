package com.example.emailcloneapp.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emailcloneapp.ui.home.data.EmailDao
import com.example.emailcloneapp.ui.home.data.EmailData

@Database(entities = [EmailData::class], version = 1, exportSchema = false)
abstract class DatabseConfig : RoomDatabase() {
    abstract fun emailDao(): EmailDao

    companion object {
        // This object can be accessed by more than one thread at the time
        @Volatile
        private var INSTANCE: DatabseConfig? = null

        fun getDatabase(context: Context): DatabseConfig {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabseConfig::class.java,
                        "spike_clone.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
package com.example.emailcloneapp.ui.home.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmailDao {
    @Query("SELECT * from email_data")
    fun getAllEmails(): List<EmailData>

    @Insert
    suspend fun insertEmail(emailData: EmailData)

    @Insert
    suspend fun insertEmails(emailData: List<EmailData>)

    @Query("DELETE from email_data")
    suspend fun deleteAllEmails()
}
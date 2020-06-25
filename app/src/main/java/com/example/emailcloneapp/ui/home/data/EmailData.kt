package com.example.emailcloneapp.ui.home.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "email_data")
data class EmailData (
    @PrimaryKey(autoGenerate = true)
    val emailId: Int,
    val username: String,
    val email: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val image: String,
    val sendingDate: String
)
package com.example.emailcloneapp.ui.home.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "email_data")
@Parcelize
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
): Parcelable
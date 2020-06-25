package com.example.emailcloneapp.ui.home.data

import retrofit2.Response
import retrofit2.http.GET

interface EmailService {
    @GET(".")
    suspend fun getEmailData(): Response<List<EmailData>>
}
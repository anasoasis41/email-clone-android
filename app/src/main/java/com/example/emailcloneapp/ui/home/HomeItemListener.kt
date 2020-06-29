package com.example.emailcloneapp.ui.home

import com.example.emailcloneapp.ui.home.data.EmailData

interface HomeItemListener {
    fun onEmailItemClick(email: EmailData)
    fun onUserItemClick(user: EmailData)
}
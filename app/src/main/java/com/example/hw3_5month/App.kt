package com.example.hw3_5month

import android.app.Application

class App : Application() {
    companion object{
        lateinit var api :PixApi
    }

    override fun onCreate() {
        super.onCreate()
        val retrofit = RetrafitService()
        api=retrofit.getApi()
    }

}
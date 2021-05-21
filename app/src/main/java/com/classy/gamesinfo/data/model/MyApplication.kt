package com.classy.gamesinfo.data.model

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Declaring a singleton instance of the SP
        MySharedPreferences.initHelper(this)
    }
}
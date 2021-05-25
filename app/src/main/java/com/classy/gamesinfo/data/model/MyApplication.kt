package com.classy.gamesinfo.data.model

import android.app.Application
import com.classy.gamesinfo.data.modules.articlesModule
import com.classy.gamesinfo.data.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Declaring a singleton instance of the SP
        MySharedPreferences.initHelper(this)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(articlesModule, networkModule))
        }
    }
}
package com.example.preapp

import android.app.Application
import com.example.preapp.ioc.ApplicationComponent
import com.example.preapp.ioc.DaggerApplicationComponent

class App: Application() {
    val applicationComponent:ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().build()
    }


    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object {
        var appInstance:App?=null
        fun getApplicationInstance():App = appInstance!!
    }
}
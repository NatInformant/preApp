package com.example.preapp

import android.app.Application
import com.example.preapp.ioc.ApplicationComponent
import com.example.preapp.ioc.DaggerApplicationComponent

class App: Application() {
    val applicationComponent:ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().build()
    }
}
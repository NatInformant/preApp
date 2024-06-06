package com.example.preapp.ioc

import android.content.Context
import com.example.preapp.App

val Context.applicationComponent: ApplicationComponent
    get() = when(this) {
        is App -> this.applicationComponent
        else -> this.applicationContext.applicationComponent
    }
package com.kareem.littlelemon.util

import android.app.Application
import android.content.Context

class LittleLemonApplication : Application() {
    init {
        application = this
    }

    companion object {
        private lateinit var application: LittleLemonApplication
        fun getApplicationContext(): Context = application.applicationContext
    }

}
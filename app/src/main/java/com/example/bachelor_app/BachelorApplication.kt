package com.example.bachelor_app

import android.app.Application
import com.example.bachelor_app.dependencies.ApplicationComponent
import com.example.bachelor_app.dependencies.ApplicationModule
import com.example.bachelor_app.dependencies.DaggerApplicationComponent

class BachelorApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()

    }

}
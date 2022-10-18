package com.example.bachelor_app

import android.app.Application
import com.example.bachelor_app.dependencies.ApplicationComponent
import com.example.bachelor_app.dependencies.DaggerApplicationComponent

class BachelorApplication : Application() {

    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()

}
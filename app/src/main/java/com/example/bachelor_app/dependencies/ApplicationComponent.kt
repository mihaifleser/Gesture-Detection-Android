package com.example.bachelor_app.dependencies

import android.content.Context
import com.example.bachelor_app.ui.fragment.LoginFragment
import com.example.bachelor_app.ui.fragment.SensorsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ManagersModule::class, ApplicationModule::class, CommunicationModule::class])
interface ApplicationComponent {
    val context: Context
    fun inject(fragment: LoginFragment)
    fun inject(fragment: SensorsFragment)
}
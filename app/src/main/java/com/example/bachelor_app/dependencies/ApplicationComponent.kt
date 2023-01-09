package com.example.bachelor_app.dependencies

import com.example.bachelor_app.ui.fragment.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ManagersModule::class])
interface ApplicationComponent {
    fun inject(fragment: LoginFragment)
}
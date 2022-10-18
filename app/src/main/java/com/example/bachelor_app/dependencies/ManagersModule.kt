package com.example.bachelor_app.dependencies

import com.example.bachelor_app.managers.FirebaseManager
import com.example.bachelor_app.managers.IFirebaseManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ManagersModule {

    @Provides
    @Singleton
    internal fun provideFirebaseManager(): IFirebaseManager {
        return FirebaseManager()
    }
}
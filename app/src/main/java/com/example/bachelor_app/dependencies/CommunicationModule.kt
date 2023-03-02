package com.example.bachelor_app.dependencies

import android.content.Context
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommunicationModule {

    @Provides
    @Singleton
    fun provideMessageClient(context: Context): MessageClient {
        return Wearable.getMessageClient(context)
    }

}
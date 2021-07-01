package com.dkitamura.crave.di.modules

import android.content.Context
import androidx.room.Room
import com.dkitamura.crave.database.CravingsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class coroutineDefault

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class coroutineMain

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class coroutineIO


    @Provides
    @coroutineIO
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @coroutineMain
    fun providerDispatcherMain(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @coroutineDefault
    fun provideDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}


package com.dkitamura.crave.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dkitamura.crave.database.CravingsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCravingDb(@ApplicationContext context: Context): CravingsDatabase {
        return Room
            .databaseBuilder(context, CravingsDatabase::class.java, CravingsDatabase.DATABASE_NAME)
            .build()
    }
}
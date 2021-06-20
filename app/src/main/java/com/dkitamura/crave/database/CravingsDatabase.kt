package com.dkitamura.crave.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dkitamura.crave.database.daos.RandomRecipeDao
import com.dkitamura.crave.models.network.randomrecipes.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class, StringListConverter::class)
abstract class CravingsDatabase : RoomDatabase() {

    abstract fun randomRecipeDao(): RandomRecipeDao

    companion object {
        const val DATABASE_NAME = "cravings-db"
    }
}


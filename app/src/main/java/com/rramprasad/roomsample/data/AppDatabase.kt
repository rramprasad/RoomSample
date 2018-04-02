package com.rramprasad.roomsample.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Recipe::class)],version = 1)
abstract class AppDatabase : RoomDatabase(){


    abstract fun recipeDao() : RecipeDao


    companion object {

        var DBINSTANCE : AppDatabase? = null

        fun getDatabase(context : Context): AppDatabase? {
            synchronized(AppDatabase::class){
                if(DBINSTANCE == null){
                    DBINSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "recipes_database"
                    ).build()
                }
            }
            return DBINSTANCE
        }
    }
}
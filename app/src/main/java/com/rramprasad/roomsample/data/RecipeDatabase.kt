package com.rramprasad.roomsample.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(RecipeEntity::class)],version = 1,exportSchema = false)
abstract class RecipeDatabase : RoomDatabase(){


    abstract fun recipeDao() : RecipeDao


    companion object {

        private var DBINSTANCE : RecipeDatabase? = null

        fun getRecipeDatabase(context : Context): RecipeDatabase? {
            if(DBINSTANCE == null){
                synchronized(RecipeDatabase::class){
                    DBINSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RecipeDatabase::class.java,
                            "RecipesDatabase.db"
                    ).build()
                }
            }
            return DBINSTANCE
        }

        fun destroyRecipeDatabaseInstance(){
            DBINSTANCE = null
        }
    }
}
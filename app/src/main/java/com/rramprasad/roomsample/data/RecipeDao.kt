package com.rramprasad.roomsample.data

import android.arch.persistence.room.*

@Dao
interface RecipeDao {

    @Query("SELECT * from recipes_entity")
    fun getAllRecipes() : List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecipes(recipes: List<RecipeEntity>)

    @Update
    fun updateRecipe(recipe: RecipeEntity)

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)

}


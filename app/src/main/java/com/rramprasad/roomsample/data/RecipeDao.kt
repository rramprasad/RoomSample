package com.rramprasad.roomsample.data

import android.arch.persistence.room.*

@Dao
interface RecipeDao {

    @Query("SELECT * from Recipe")
    fun getAllRecipes() : List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecipes(recipes: List<Recipe>)

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)

}


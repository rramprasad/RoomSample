package com.rramprasad.roomsample.data

import android.arch.persistence.room.*

@Dao
interface RecipeDao {

    //Recipes
    @Query("SELECT * from recipes_entity")
    fun getAllRecipes() : List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecipes(recipes: List<RecipeEntity>)

    @Update
    fun updateRecipe(recipe: RecipeEntity)

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)


    //Recipe Steps
    @Query("SELECT * from recipe_steps_entity")
    fun getAllRecipeSteps() : List<RecipeStepEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipeStep(recipeStep: RecipeStepEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecipesSteps(recipeSteps: List<RecipeStepEntity>)

    @Update
    fun updateRecipeStep(recipeStep: RecipeStepEntity)

    @Delete
    fun deleteRecipeStep(recipeStep: RecipeStepEntity)


    //Ingredients
    @Query("SELECT * from ingredients_entity")
    fun getAllIngredients() : List<IngredientEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredient(ingredient: IngredientEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllIngredients(ingredients: List<IngredientEntity>)

    @Update
    fun updateIngredient(ingredient: IngredientEntity)

    @Delete
    fun deleteIngredient(ingredient: IngredientEntity)
}


package com.rramprasad.roomsample.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "ingredients_entity")
data class IngredientEntity(

        @PrimaryKey(autoGenerate = true)
        var ingredientId: Int? = null,

        var recipeId: Long?,
        var quantity: Double,
        var measure: String,
        var ingredient: String
){
    constructor() : this(null,0,0.0,"","")
}
package com.rramprasad.roomsample.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
        var recipes: List<Recipe>
)

data class Recipe(

        @SerializedName("id")
        var id: Int,

        @SerializedName("name")
        var name: String,

        @SerializedName("ingredients")
        var ingredients: List<Ingredient>,

        @SerializedName("steps")
        var steps: List<RecipeStep>,

        @SerializedName("servings")
        var servings: String,

        @SerializedName("image")
        var imageURL: String
)

data class RecipeStep(

        @SerializedName("id")
        var stepId: Int,

        @SerializedName("shortDescription")
        var shortDescription: String,

        @SerializedName("description")
        var description: String,

        @SerializedName("videoURL")
        var videoURL: String,

        @SerializedName("thumbnailURL")
        var thumbnailURL: String
)

data class Ingredient(

        @SerializedName("quantity")
        var quantity: Double,

        @SerializedName("measure")
        var measure: String,

        @SerializedName("ingredient")
        var ingredient: String
)
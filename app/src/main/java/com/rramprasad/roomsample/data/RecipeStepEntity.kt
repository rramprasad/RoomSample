package com.rramprasad.roomsample.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recipe_steps_entity")
data class RecipeStepEntity(

        @PrimaryKey(autoGenerate = true)
        var stepId : Int? = null,

        var id: Int,
        var recipeId: Long?,
        var shortDescription: String,
        var description: String,
        var videoURL: String,
        var thumbnailURL: String
){
    constructor() : this(null,0,0,"","","","")
}

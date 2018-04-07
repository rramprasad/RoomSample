package com.rramprasad.roomsample.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recipes_entity")
data class RecipeEntity(

        @PrimaryKey
        var id: Int,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "servings")
        var servings: String,

        @ColumnInfo(name = "image")
        var imageURL: String
){
        constructor() : this(0,"","","")
}
package com.rramprasad.roomsample.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class Recipe(
        @PrimaryKey @SerializedName("id") var id: Int = 0,
        @ColumnInfo(name = "name") @SerializedName("name") var name: String = "",
        @Embedded @SerializedName("ingredients") var ingredients: List<Ingredient>? = null,
        @Embedded @SerializedName("steps") var steps: List<RecipeStep>? = null,
        @ColumnInfo(name = "servings") @SerializedName("servings") var servings: String = "",
        @ColumnInfo(name = "image") @SerializedName("image") var imageURL: String = ""
        ) /*: Parcelable*/ {

        constructor() : this(0)

        /*constructor() : this()

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.createTypedArrayList(Ingredient),
                parcel.createTypedArrayList(RecipeStep),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(name)
            parcel.writeTypedList(ingredients)
            parcel.writeTypedList(steps)
            parcel.writeString(servings)
            parcel.writeString(imageURL)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Recipe> {
            override fun createFromParcel(parcel: Parcel): Recipe {
                return Recipe(parcel)
            }

            override fun newArray(size: Int): Array<Recipe?> {
                return arrayOfNulls(size)
            }
        }*/
}

data class Ingredient(
        @SerializedName("quantity")
        val quantity : Int,
        @SerializedName("measure")
        val measure : String,
        @SerializedName("ingredient")
        val ingredient : String
) /*: Parcelable*/ {
    /*constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(quantity)
        parcel.writeString(measure)
        parcel.writeString(ingredient)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }*/

}

data class RecipeStep(
        @SerializedName("id")
        val stepId : Int,
        @SerializedName("shortDescription")
        val shortDescription : String,
        @SerializedName("description")
        val description : String,
        @SerializedName("videoURL")
        val videoURL : String,
        @SerializedName("thumbnailURL")
        val thumbnailURL : String
) /*: Parcelable*/ {
    /*constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(stepId)
        parcel.writeString(shortDescription)
        parcel.writeString(description)
        parcel.writeString(videoURL)
        parcel.writeString(thumbnailURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeStep> {
        override fun createFromParcel(parcel: Parcel): RecipeStep {
            return RecipeStep(parcel)
        }

        override fun newArray(size: Int): Array<RecipeStep?> {
            return arrayOfNulls(size)
        }
    }*/

}
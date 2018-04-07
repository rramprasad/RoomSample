package com.rramprasad.roomsample

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rramprasad.roomsample.data.Recipe
import com.rramprasad.roomsample.data.RecipeEntity
import com.squareup.picasso.Picasso

class RecipesAdapter(private val recipesArrayList: ArrayList<RecipeEntity>) : RecyclerView.Adapter<RecipesAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item,parent,false)
        return CustomViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipesArrayList.size
    }

    override fun onBindViewHolder(holder:CustomViewHolder, position: Int) {

        val imageURL = recipesArrayList[position].imageURL

        if(imageURL.isNotEmpty()){
            Picasso.get()
                    .load(imageURL)
                    .placeholder(android.R.drawable.gallery_thumb)
                    .resize(50,50)
                    .centerCrop()
                    .into(holder.mRecipeImageView)
        }


        holder.mRecipeNameTextView.setText(recipesArrayList[position].name)
        holder.mRecipeServingsTextView.setText(recipesArrayList[position].servings)
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mRecipeImageView: ImageView
        val mRecipeNameTextView: TextView
        val mRecipeServingsTextView: TextView

        init {
            mRecipeImageView = itemView.findViewById<ImageView>(R.id.recipe_imageview)
            mRecipeNameTextView = itemView.findViewById<TextView>(R.id.recipe_name_textview)
            mRecipeServingsTextView = itemView.findViewById<TextView>(R.id.recipe_servings_textview)

        }
    }
}

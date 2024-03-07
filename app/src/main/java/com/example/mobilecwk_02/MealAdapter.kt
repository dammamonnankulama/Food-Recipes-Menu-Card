package com.example.mobilecwk_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class MealAdapter(private val meals: ArrayList<String>) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mealNameTextView: TextView = view.findViewById(R.id.textViewMealName)
        val mealImageView: ImageView = view.findViewById(R.id.imageViewMeal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mealNameTextView.text = meals[position]


        Glide.with(holder.itemView.context)
            .load("https://source.unsplash.com/random")
            .placeholder(R.drawable.placeholder_image)
            .into(holder.mealImageView)
    }

    override fun getItemCount() = meals.size
}

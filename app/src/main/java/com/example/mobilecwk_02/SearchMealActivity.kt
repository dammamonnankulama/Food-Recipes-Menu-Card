package com.example.mobilecwk_02

import com.bumptech.glide.Glide
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException

class SearchMealActivity : AppCompatActivity(R.layout.activity_search_meal) {

    private lateinit var searchMealEditText: TextInputEditText
    private lateinit var searchMealButton: Button
    private lateinit var mealDetailsTextView: TextView
    private lateinit var requestQueue: RequestQueue
    private lateinit var mealThumbnailImageView: ImageView

    private val mealDbApiBaseUrl = "https://www.themealdb.com/api/json/v1/1/search.php"
    private var searchQuery = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal)
        setupViews()
        setupListeners()
    }

    private fun setupViews() {
        searchMealEditText = findViewById(R.id.search_meal_edit_text)
        searchMealButton = findViewById(R.id.search_meal_button)
        mealDetailsTextView = findViewById(R.id.meal_details_text_view)


        requestQueue = Volley.newRequestQueue(this)
    }

    private fun setupListeners() {
        searchMealEditText.doAfterTextChanged { searchMealsByNameOrIngredient(it.toString()) }
        searchMealButton.setOnClickListener { searchMealsByNameOrIngredient(searchMealEditText.text.toString()) }
    }

    private fun searchMealsByNameOrIngredient(query: String) {
        val url = "$mealDbApiBaseUrl?s=$query"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val mealsArray = response.getJSONArray("meals")
                    val mealDetails = StringBuilder()
                    for (i in 0 until mealsArray.length()) {
                        val mealObject = mealsArray.getJSONObject(i)
                        mealDetails.append("${mealObject.getString("strMeal")}\nCategory: ${mealObject.getString("strCategory")}\nInstructions: ${mealObject.getString("strInstructions")}\nIngredients: \n")

                        for (j in 1..20) {
                            val ingredient = mealObject.getString("strIngredient$j")
                            val measurement = mealObject.getString("strMeasure$j")

                            if (!ingredient.isNullOrEmpty() && !measurement.isNullOrEmpty()) {
                                mealDetails.append("- $measurement of $ingredient\n")
                            }
                        }

                        mealDetails.append("\n\n")

                        val thumbnailUrl = mealObject.getString("strMealThumb")
                        Glide.with(this)
                            .load(thumbnailUrl)
                            .centerCrop()

                    }

                    mealDetailsTextView.text = mealDetails.toString()
                } catch (e: JSONException) {
                    Log.e("SearchMealActivity", "No results", e)
                    mealDetailsTextView.text = "No results"
                }
            },
            { error ->
                Log.e("SearchMealActivity", "API request", error)
                mealDetailsTextView.text = "API Connection Lost"
            }
        )

        requestQueue.add(jsonObjectRequest)
    }

}

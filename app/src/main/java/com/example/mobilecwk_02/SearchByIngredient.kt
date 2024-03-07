package com.example.mobilecwk_02

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class SearchByIngredient : AppCompatActivity() {

    private lateinit var searchIngredientEditText: EditText
    private lateinit var outputTextView: TextView
    private lateinit var retrieveButton: Button
    private var searchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_ingredient)

        searchIngredientEditText = findViewById(R.id.searchIngredient)
        outputTextView = findViewById(R.id.outputView)
        retrieveButton = findViewById(R.id.bttnRetrieve)

        retrieveButton.setOnClickListener {
            val ingredient = searchIngredientEditText.text.toString()
            retrieveMealsByIngredient(ingredient)
        }
    }

    private fun retrieveMealsByIngredient(ingredient: String) {

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.themealdb.com/api/json/v1/1/filter.php?i=$ingredient")
                    .build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseData = response.body()?.string()
                    val jsonObject = JSONObject(responseData)
                    val mealsValue = jsonObject.opt("meals")
                    val mealsArray = if (mealsValue is JSONArray) {
                        mealsValue
                    } else {
                        JSONArray()
                    }
                    if (mealsArray.length() > 0) {

                        for (i in 0 until mealsArray.length()) {
                            val meal = mealsArray.getJSONObject(i)
                            val mealName = meal.optString("strMeal")
                            val mealIngredients = StringBuilder()
                            for (j in 1..20) {
                                val ingredientKey = "strIngredient$j"
                                val measureKey = "strMeasure$j"
                                val ingredient = meal.optString(ingredientKey)
                                val measure = meal.optString(measureKey)
                                if (!ingredient.isNullOrEmpty()) {
                                    mealIngredients.append("$ingredient - $measure\n")
                                }
                            }

                            GlobalScope.launch(Dispatchers.Main) {
                                outputTextView.append("Meal: $mealName\n" )

                            }
                        }
                    } else {
                        GlobalScope.launch(Dispatchers.Main) {
                            outputTextView.text = ("No matches.")
                        }
                    }
                } else {
                    GlobalScope.launch(Dispatchers.Main) {
                        outputTextView.text = "Failed to retrieve meals "
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}

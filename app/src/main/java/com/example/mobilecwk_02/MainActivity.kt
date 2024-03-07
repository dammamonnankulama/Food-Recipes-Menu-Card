package com.example.mobilecwk_02

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    lateinit var mealDB: MealDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mealDB = Room.databaseBuilder(applicationContext, MealDatabase::class.java, "mealDB").build()

        val addMeals = findViewById<Button>(R.id.addMeals)
        addMeals.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                storeMealstoDB()
                withContext(Dispatchers.Main) {
                    println("Added Meals!")

                }


            }
        }
        val searchIngredient = findViewById<Button>(R.id.searchByIngred)
        searchIngredient.setOnClickListener{
            val intent = Intent(this, SearchByIngredient::class.java)
            startActivity(intent)
        }

        val searchMeal = findViewById<Button>(R.id.searchMeals)
        searchMeal.setOnClickListener{
            val intent1 = Intent(this, SearchMealActivity::class.java)
            startActivity(intent1)
        }

    }

    class MealAdapter(private val meals: List<meal>) :
        RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

        class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)

            fun bind(meal: meal) {
                Picasso.get().load(meal.Thumbnail).into(thumbnailImageView)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_meal, parent, false)
            return MealViewHolder(view)
        }

        override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
            val meal = meals[position]
            holder.bind(meal)
        }

        override fun getItemCount(): Int {
            return meals.size
        }
    }



    private fun storeMealstoDB() {
        val mealno1 = meal(
            id = 1,
            Meal = "Sweet and Sour Pork",
            DrinkAlternate = null,
            Category = "Pork",
            Area = "Chinese",
            Instructions = "Preparation\r\n1. Crack the egg into a bowl. Separate the egg white and yolk.\r\n\r\nSweet and Sour Pork\r\n2. Slice the pork tenderloin into ips.\r\n\r\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and an egg white.\r\n\r\n4. Marinade the pork ips for about 20 minutes.\r\n\r\n5. Put the remaining starch in a bowl. Add some water and vinegar to make a starchy sauce.\r\n\r\nSweet and Sour Pork\r\nCooking Inuctions\r\n1. Pour the cooking oil into a wok and heat to 190\u00b0C (375\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\r\n\r\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\r\n\r\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\r\n\r\n4. Pour in the starchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\r\n\r\n5. Serve on a plate and add some coriander for decoration.",
            MealThumb = "https://www.themealdb.com/images/media/meals/1529442316.jpg",
            Tags = "Sweet",
            Youtube = "https://www.youtube.com/watch?v=mdaBIhgEAMo",
            Ingredient1 = "Pork",
            Ingredient2 = "Egg",
            Ingredient3 = "Water",
            Ingredient4 = "Salt",
            Ingredient5 = "Sugar",
            Ingredient6 = "Soy Sauce",
            Ingredient7 = "Starch",
            Ingredient8 = "Tomato Puree",
            Ingredient9 = "Vinegar",
            Ingredient10 = "Coriander",
            Ingredient11 = null,
            Ingredient12 = null,
            Ingredient13 = null,
            Ingredient14 = null,
            Ingredient15 = null,
            Ingredient16 = null,
            Ingredient17 = null,
            Ingredient18 = null,
            Ingredient19 = null,
            Ingredient20 = null,
            Measure1 = "200g",
            Measure2 = "1",
            Measure3 = "Dash",
            Measure4 = "1/2 tsp",
            Measure5 = "1 tsp",
            Measure6 = "10g",
            Measure7 = "10g",
            Measure8 = "30g",
            Measure9 = "10g",
            Measure10 = "Dash",
            Measure11 = null,
            Measure12 = null,
            Measure13 = null,
            Measure14 = null,
            Measure15 = null,
            Measure16 = null,
            Measure17 = null,
            Measure18 = null,
            Measure19 = null,
            Measure20 = null,
            Measure21 = null,
            Source = null,
            ImageSource = null,
            CreativeCommonsConfirmed = null,
            dateModified = null,
            Thumbnail = "https://www.themealdb.com/images/media/meals/1529442316.jpg",
        )
        val mealno2 = meal(
            id = 2,
            Meal = "Chicken Marengo",
            DrinkAlternate = null,
            Category = "Chicken",
            Area = "French",
            Instructions = "Heat the oil in a large flameproof casserole dish and stir-fry the mushrooms until they start to soften. Add the chicken legs and cook briefly on each side to colour them a little.\\r\\nPour in the passata, crumble in the stock cube and stir in the olives. Season with black pepper \\u2013 you shouldn\\u2019t need salt. Cover and simmer for 40 mins until the chicken is tender. Sprinkle with parsley and serve with pasta and a salad, or mash and green veg, if you like.",
            MealThumb = "https://www.themealdb.com/images/media/meals/qpxvuq1511798906.jpg",
            Tags = "null",
            Youtube = "https://www.youtube.com/watch?v=U33HYUr-0Fw",
            Ingredient1 = "Olive Oil",
            Ingredient2 = "Mushrooms",
            Ingredient3 = "Chicken Legs",
            Ingredient4 = "Passata",
            Ingredient5 = "Chicken Stock Cube",
            Ingredient6 = "Black Olives",
            Ingredient7 = "Parsley",
            Ingredient8 = null,
            Ingredient9 = null,
            Ingredient10 = null,
            Ingredient11 = null,
            Ingredient12 = null,
            Ingredient13 = null,
            Ingredient14 = null,
            Ingredient15 = null,
            Ingredient16 = null,
            Ingredient17 = null,
            Ingredient18 = null,
            Ingredient19 = null,
            Ingredient20 = null,
            Measure1 = "1 tbs",
            Measure2 = "300g",
            Measure3 = "4",
            Measure4 = "500g",
            Measure5 = "1",
            Measure6 = "100g",
            Measure7 = "Chopped",
            Measure8 = null,
            Measure9 = null,
            Measure10 = null,
            Measure11 = null,
            Measure12 = null,
            Measure13 = null,
            Measure14 = null,
            Measure15 = null,
            Measure16 = null,
            Measure17 = null,
            Measure18 = null,
            Measure19 = null,
            Measure20 = null,
            Measure21 = null,
            Source = null,
            ImageSource = null,
            CreativeCommonsConfirmed = null,
            dateModified = null,
            Thumbnail = "https://www.themealdb.com/images/media/meals/qpxvuq1511798906.jpg",
            )
        val mealno3 = meal(
            id = 3,
            Meal = "Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
            DrinkAlternate = null,
            Category = "Beef",
            Area = "Vietnamese",
            Instructions = "Add'l ingredients: mayonnaise, siracha\\r\\n\\r\\n1\\r\\n\\r\\nPlace rice in a fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, then cover and reduce heat to low. Cook until rice is tender, 15 minutes. Keep covered off heat for at least 10 minutes or until ready to serve.\\r\\n\\r\\n2\\r\\n\\r\\nMeanwhile, wash and dry all produce. Peel and finely chop garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. Halve, peel, and medium dice onion. Trim, peel, and grate carrot.\\r\\n\\r\\n3\\r\\n\\r\\nIn a medium bowl, combine cucumber, juice from half the lime, \\u00bc tsp sugar (\\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much sriracha as you\\u2019d like. Season with salt and pepper.\\r\\n\\r\\n4\\r\\n\\r\\nHeat a drizzle of oil in a large pan over medium-high heat. Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. Turn off heat; taste and season with salt and pepper.\\r\\n\\r\\n5\\r\\n\\r\\nFluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice. Drizzle with sriracha mayo.\",\n",

            MealThumb = "https://www.themealdb.com/images/media/meals/z0ageb1583189517.jpg",
            Tags = "null",
            Youtube = "",
            Ingredient1 = "Rice",
            Ingredient2 = "Onion",
            Ingredient3 = "Lime",
            Ingredient4 = "Garlic Clove",
            Ingredient5 = "Cucumber",
            Ingredient6 = "Carrots",
            Ingredient7 = "Ground Beef",
            Ingredient8 = "Soy Sauce",
            Ingredient9 = "",
            Ingredient10 = null,
            Ingredient11 = null,
            Ingredient12 = null,
            Ingredient13 = null,
            Ingredient14 = null,
            Ingredient15 = null,
            Ingredient16 = null,
            Ingredient17 = null,
            Ingredient18 = null,
            Ingredient19 = null,
            Ingredient20 = null,
            Measure1 = "White",
            Measure2 = "1",
            Measure3 = "1",
            Measure4 = "3",
            Measure5 = "1",
            Measure6 = "3 oz",
            Measure7 = "1 lb",
            Measure8 = "2 oz",
            Measure9 = null,
            Measure10 = null,
            Measure11 = null,
            Measure12 = null,
            Measure13 = null,
            Measure14 = null,
            Measure15 = null,
            Measure16 = null,
            Measure17 = null,
            Measure18 = null,
            Measure19 = null,
            Measure20 = null,
            Measure21 = null,
            Source = null,
            ImageSource = null,
            CreativeCommonsConfirmed = null,
            dateModified = null,
            Thumbnail = "https://www.themealdb.com/images/media/meals/z0ageb1583189517.jpg"
        )

        val mealno4 = meal(
            id = 4,
            Meal = "Leblebi Soup",
            DrinkAlternate = null,
            Category = "Vegetarian",
            Area = "Tunisian",
            Instructions = "Heat the oil in a large pot. Add the onion and cook until translucent.\r\nDrain the soaked chickpeas and add them to the pot together with the vegetable stock. Bring to the boil, then reduce the heat and cover. Simmer for 30 minutes.\r\nIn the meantime toast the cumin in a small ungreased frying pan, then grind them in a mortar. Add the garlic and salt and pound to a fine paste.\r\nAdd the paste and the harissa to the soup and simmer until the chickpeas are tender, about 30 minutes.\r\nSeason to taste with salt, pepper and lemon juice and serve hot.",

            MealThumb = "https://www.themealdb.com/images/media/meals/x2fw9e1560460636.jpg",
            Tags = "Soup",
            Youtube = "https://www.youtube.com/watch?v=BgRifcCwinY",
            Ingredient1 = "Olive Oil",
            Ingredient2 = "Onion",
            Ingredient3 = "Chickpeas",
            Ingredient4 = "Vegetable Stock",
            Ingredient5 = "Cumin",
            Ingredient6 = "Garlic",
            Ingredient7 = "Salt",
            Ingredient8 = "Harissa Spice",
            Ingredient9 = "Pepper",
            Ingredient10 = "Lime",
            Ingredient11 = null,
            Ingredient12 = null,
            Ingredient13 = null,
            Ingredient14 = null,
            Ingredient15 = null,
            Ingredient16 = null,
            Ingredient17 = null,
            Ingredient18 = null,
            Ingredient19 = null,
            Ingredient20 = null,
            Measure1 = "2 tbs",
            Measure2 = "1 medium finely diced",
            Measure3 = "250g",
            Measure4 = "1.5L",
            Measure5 = "1 tsp",
            Measure6 = "5 cloves",
            Measure7 = "1/2 tsp",
            Measure8 = "1 tsp",
            Measure9 = "Pinch",
            Measure10 = "1/2",
            Measure11 = null,
            Measure12 = null,
            Measure13 = null,
            Measure14 = null,
            Measure15 = null,
            Measure16 = null,
            Measure17 = null,
            Measure18 = null,
            Measure19 = null,
            Measure20 = null,
            Measure21 = null,
            Source = "http://allrecipes.co.uk/recipe/43419/leblebi--tunisian-chickpea-soup-.aspx",
            ImageSource = null,
            CreativeCommonsConfirmed = null,
            dateModified = null,
            Thumbnail = "https://www.themealdb.com/images/media/meals/x2fw9e1560460636.jpg"
        )

    }

}








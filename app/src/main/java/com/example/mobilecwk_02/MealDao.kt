package com.example.mobilecwk_02

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: meal)

    @Query("SELECT * FROM Meal")
    fun getAllMeals(): List<meal>


}

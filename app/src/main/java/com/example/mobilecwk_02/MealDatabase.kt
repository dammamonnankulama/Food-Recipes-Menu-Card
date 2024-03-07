package com.example.mobilecwk_02

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [meal::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object{
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun viewDatabase(context: Context): MealDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    name = "meal_database"
                ).build()
                INSTANCE = instance
                return instance

            }
        }
    }

}

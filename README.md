# MobileProject2023
## Project Overview
Done throughout my second year of College in Computer Science for my Mobile Application Development 1: Android class with Kotlin.
It is a Fitness Tracker App that calculates Display total calories burned and total stretching duration for the current month and for
the past month.

## Table of Contents
- [Project Description](#project-description)
- [Installation](#installation)
- [UI](#ui)
- [Usage](#usage)
- [Features](#features)
- [Future Improvements Ideas](#improvements)

<a name="project-description"></a>
## Project Description 
- The project is developed using Android Studio. It allows a user to input stretches and exercises. Each stretch and exercise has
a trainer. For each stretch and exercise, a date should be inputted. The date input allows for the separation of the exercises and
stretches when calculating the total calories burned and total stretching duration for the current month and for the past month.
- Multiple layouts, activities, and fragments are used. Here are some examples of the UI (*the layouts shown here mostly display
the CRUD operations that could be done for exercises but trainers and stretches also use similar layouts)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/96471656-38f5-41e3-b3e3-f9ce632dcbbf)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/145c6383-ffca-4c8e-810a-4571ec858c5b)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/343bd157-eea9-4158-bcda-e8ea1baf83ab)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/b93517ea-a8f2-445f-916d-190fc55d8afb)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/cbcaa361-edae-4291-af10-c3d74cfd5006)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/8730237c-02e2-42ab-8a2b-30c21a423fe2)
![image](https://github.com/Mila5847/MobileProject2023/assets/46633364/27026739-1e57-4ce5-a297-caa4a5102a00)


<a name="installation"></a>
## Installation 
1. Clone the repository
2. Navigate to the project directory
3. Run the project
4. Experiment and Enjoy!

<a name="ui"></a>
## UI
- Android Studio provides a cellphone simulation allowing the user to use the mobile application.

<a name="usage"></a>
## Usage 
This app could have been developed in a simpler way, allowing the user to only be able to perform CRUD operations on trainers, stretches,
and exercises. However, an interesting experience would be to do something with the data given by the user. That is why I decided to make
this app more useful by providing a tracker for calories burned and stretch durations. This app is useful not only to store data, but also keep track of the user's performance. 

<a name="features"></a>
## Features 
The 3-layer web app allows the user to do the following:
- View, create, update, delete a trainer.
- View, create, update, delete a stretch
- View, create, update, delete an exercise
- Display total calories burned and total stretching duration for the current month and for the past month.

<a name="difficulties"></a>
## Difficulties
- Using SQLite was quite challenging. If the database needed to be reset, it must first be removed from the project and rebuilt again. This link helped a lot: https://www.geeksforgeeks.org/how-to-view-and-locate-sqlite-database-in-android-studio/
- Part of my database looks like this:
```kotlin
private inner class CustomSQLiteOpenHelper(
    context: Context
) : SQLiteOpenHelper(
    context, DB_NAME,
    null, DB_VERSION
) {
    // This function only runs the first time the database is created
    override fun onCreate(db: SQLiteDatabase) {
        // Create a table for workouts and all their details, including trainer ID
        val workoutsTable = ("CREATE TABLE $TABLE_WORKOUT (" +
            "$TABLE_ROW_EXERCISE_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "$TABLE_ROW_EXERCISE TEXT NOT NULL, " +
            "$TABLE_ROW_SETS INTEGER, " +
            "$TABLE_ROW_REPS INTEGER, " +
            "$TABLE_ROW_WEIGHT INTEGER, " +
            "$TABLE_ROW_CALORIES INTEGER, " + // optional
            "$TABLE_ROW_DATE TEXT NOT NULL, " +
            "$TABLE_ROW_TRAINER_ID INTEGER NOT NULL, " +
            "$TABLE_ROW_STRETCH_ID INTEGER, " + // foreign key column
            "FOREIGN KEY($TABLE_ROW_TRAINER_ID) REFERENCES $TABLE_TRAINER($TABLE_ROW_TRAINER_ID), " +
            "FOREIGN KEY($TABLE_ROW_STRETCH_ID) REFERENCES $TABLE_STRETCH($TABLE_ROW_STRETCH_ID)" +
            ");")
        db.execSQL(workoutsTable)

        // Create a table for trainers
        val trainersTable = ("CREATE TABLE $TABLE_TRAINER (" +
            "$TABLE_ROW_TRAINER_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "$TABLE_ROW_TRAINER_FIRST_NAME TEXT NOT NULL, " +
            "$TABLE_ROW_TRAINER_LAST_NAME TEXT NOT NULL" +
            ");")
        db.execSQL(trainersTable)

        // Create a table for stretches
        val stretchesTable = ("CREATE TABLE $TABLE_STRETCH (" +
            "$TABLE_ROW_STRETCH_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "$TABLE_ROW_STRETCH_NAME TEXT NOT NULL, " +
            "$TABLE_ROW_STRETCH_DURATION INTEGER NOT NULL, " +
            "$TABLE_ROW_STRETCH_DATE TEXT NOT NULL, " +
            "$TABLE_ROW_TRAINER_ID INTEGER NOT NULL, " +
            "FOREIGN KEY($TABLE_ROW_TRAINER_ID) REFERENCES $TABLE_TRAINER($TABLE_ROW_TRAINER_ID)" +
            ");")
        db.execSQL(stretchesTable)
    }
}
```
- Get total calories burned for the past month function:
```kotlin
    fun getTotalCaloriesBurnedUpForThePastMonth(): Int {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val lastMonthYear = calendar.get(Calendar.YEAR)
        val lastMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val threeMonthsAgoMonthFormatted = String.format("%02d", lastMonth) // Zero-padding the month if necessary

        val query =
            "SELECT SUM($TABLE_ROW_CALORIES) FROM $TABLE_WORKOUT WHERE SUBSTR($TABLE_ROW_DATE, 6, 2) <= '$threeMonthsAgoMonthFormatted' AND SUBSTR($TABLE_ROW_DATE, 1, 4) <= '$lastMonthYear'"
        val cursor = db.rawQuery(query, null)
        var totalCalories = 0
        if (cursor.moveToFirst()) {
            totalCalories = cursor.getInt(0)
        }
        cursor.close()
        return totalCalories
    }
```
- Get total calories burned for the current month function:
```kotlin 
    fun getTotalCaloriesBurnedForTheMonth(exerciseId: String): Int {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val currentMonthFormatted = String.format("%02d", currentMonth) // Zero-padding the month if necessary

        val query = "SELECT SUM($TABLE_ROW_CALORIES) FROM $TABLE_WORKOUT WHERE $TABLE_ROW_EXERCISE_ID = '$exerciseId' AND SUBSTR($TABLE_ROW_DATE, 6, 2) = '$currentMonthFormatted' AND SUBSTR($TABLE_ROW_DATE, 1, 4) = '$currentYear'"
        val cursor = db.rawQuery(query, null)
        var totalCalories = 0
        if (cursor.moveToFirst()) {
            totalCalories = cursor.getInt(0)
        }
        cursor.close()
        return totalCalories
    }
 ```
      
<a name="improvements"></a>
## Future Improvements Ideas
- I would like to modify the app so the calories are calculated based on the user's gender, age, and exercise sets and reps instead of 
only adding the exercises' calories (inputted by the user) together.
- I would like to provide more statistics to the user. For instance, comparing the calories burned throughout the months and keeping track
of the user's improvement will be a good feature to develop

package com.example.mobileproject

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.Calendar

class DataManager (context: Context) {
    private val db: SQLiteDatabase

    init {
        val dbHelper = CustomSQLiteOpenHelper(context)
        db = dbHelper.writableDatabase
    }

    companion object {
        const val TABLE_ROW_EXERCISE_ID = "exercise_id"
        const val TABLE_ROW_EXERCISE = "exercise"
        const val TABLE_ROW_SETS = "sets"
        const val TABLE_ROW_REPS = "reps"
        const val TABLE_ROW_WEIGHT = "weight"
        const val TABLE_ROW_CALORIES = "calories"
        const val TABLE_ROW_DATE = "date"

        const val TABLE_ROW_TRAINER_ID = "trainer_id"
        const val TABLE_ROW_TRAINER_FIRST_NAME = "first_name"
        const val TABLE_ROW_TRAINER_LAST_NAME = "last_name"

        const val TABLE_ROW_STRETCH_ID = "stretch_id"
        const val TABLE_ROW_STRETCH_NAME = "name"
        const val TABLE_ROW_STRETCH_DURATION= "duration"
        const val TABLE_ROW_STRETCH_DATE = "date"

        private const val DB_NAME = "workouts_db"
        private const val DB_VERSION = 1
        private const val TABLE_WORKOUT = "workouts_table"
        private const val TABLE_TRAINER = "trainers_table"
        private const val TABLE_STRETCH = "stretches_table"

    }

    // Insert Exercise
    fun insertExercise(
        exercise: String,
        sets: String,
        reps: String,
        weight: String,
        calories: String,
        date: String,
        trainerId: String,
        stretchId: String
    ) {
        val query = "INSERT INTO $TABLE_WORKOUT (" +
                "$TABLE_ROW_EXERCISE, " +
                "$TABLE_ROW_SETS, " +
                "$TABLE_ROW_REPS, " +
                "$TABLE_ROW_WEIGHT, " +
                "$TABLE_ROW_CALORIES, " +
                "$TABLE_ROW_DATE, " +
                "$TABLE_ROW_TRAINER_ID, " +
                "$TABLE_ROW_STRETCH_ID" +
                ") " +
                "VALUES (" +
                "'$exercise', " +
                "'$sets', " +
                "'$reps', " +
                "'$weight', " +
                "'$calories', " +
                "'$date', " +
                "'$trainerId', " +
                "'$stretchId'" +
                ");"
        Log.i("insert() = ", query)
        db.execSQL(query)
    }

    // Delete Exercise
    fun deleteExercise(name: String) {
        val calories = getCaloriesForExercisePackage(name);
        Constants.totalCalories -= calories
        Log.i(Constants.totalCalories.toString(), "CALORIES UPDATED")
        val query = "DELETE FROM " + TABLE_WORKOUT +
                " WHERE " + TABLE_ROW_EXERCISE +
                " = '" + name + "';"
        Log.i("delete()", query)
        db.execSQL(query)
    }

    fun updateExercise(
        id: Int,
        exercise: String,
        sets: String,
        reps: String,
        weight: String,
        calories: String,
        date: String,
        trainerId: String,
        stretchId: String
    ) {
        // Get the current calories for the exercise
        val currentCalories = getCaloriesForExercisePackage(exercise)

        // Calculate the difference in calories
        val caloriesDifference = calories.toInt() - currentCalories

        val query = "UPDATE $TABLE_WORKOUT " +
                "SET $TABLE_ROW_EXERCISE='$exercise'," +
                "$TABLE_ROW_SETS=$sets," +
                "$TABLE_ROW_REPS=$reps," +
                "$TABLE_ROW_WEIGHT=$weight," +
                "$TABLE_ROW_CALORIES=$calories," +
                "$TABLE_ROW_DATE='$date'," +
                "$TABLE_ROW_TRAINER_ID='$trainerId'," +
                "$TABLE_ROW_STRETCH_ID='$stretchId'" +
                " WHERE $TABLE_ROW_EXERCISE_ID=$id;"

        Log.i("updateExercise() = ", query)
        db.execSQL(query)
        Constants.totalCalories += caloriesDifference
    }

    // Select All Exercises
    fun selectAllExercises(): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_WORKOUT + " ORDER BY " + TABLE_ROW_DATE + " DESC", null)
    }

    // Select Exercise By Name
    fun searchExerciseName(name: String): Cursor {
        val query = "SELECT * FROM $TABLE_WORKOUT " +
                "WHERE $TABLE_ROW_EXERCISE = '$name'"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    fun searchExerciseById(id: Int): Cursor {
        val query = "SELECT " +
                "$TABLE_ROW_EXERCISE_ID," +
                "$TABLE_ROW_EXERCISE," +
                "$TABLE_ROW_SETS," +
                "$TABLE_ROW_REPS," +
                "$TABLE_ROW_WEIGHT," +
                "$TABLE_ROW_CALORIES," +
                "$TABLE_ROW_DATE," +
                "$TABLE_ROW_TRAINER_ID," +
                "$TABLE_ROW_STRETCH_ID" +
                " FROM " +
                TABLE_WORKOUT +
                " WHERE " +
                "$TABLE_ROW_EXERCISE_ID=$id;"
        Log.i("searchExerciseById() = ", query)
        return db.rawQuery(query, null)
    }

    // Insert Trainer
    fun insertTrainer(firstName: String, lastName: String) {
        val query = "INSERT INTO $TABLE_TRAINER (" +
                "$TABLE_ROW_TRAINER_FIRST_NAME, " +
                "$TABLE_ROW_TRAINER_LAST_NAME" +
                ") " +
                "VALUES (" +
                "'$firstName', " +
                "'$lastName'" +
                ");"
        Log.i("insert() = ", query)
        db.execSQL(query)
    }

    // Delete Trainer
    fun deleteTrainer(name: String) {
        val query = "DELETE FROM " + TABLE_TRAINER +
                " WHERE " + TABLE_ROW_TRAINER_FIRST_NAME +
                " = '" + name + "';"
        Log.i("delete()", query)
        db.execSQL(query)
    }

    // Update Trainer
    fun updateTrainer(id: Int, firstName: String, lastName: String) {
        val query = "UPDATE $TABLE_TRAINER " +
                "SET $TABLE_ROW_TRAINER_FIRST_NAME='$firstName'," +
                "$TABLE_ROW_TRAINER_LAST_NAME='$lastName'" +
                " WHERE $TABLE_ROW_TRAINER_ID=$id;"
        Log.i("updateTrainer() = ", query)
        db.execSQL(query)
    }

    // Select All Trainers
    fun selectAllTrainers(): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_TRAINER + " ORDER BY " + TABLE_ROW_TRAINER_FIRST_NAME + " ASC", null)
    }

    // Select Trainer By Name
    fun searchTrainerName(name: String): Cursor {
        val query = "SELECT * FROM $TABLE_TRAINER " +
                "WHERE $TABLE_ROW_TRAINER_FIRST_NAME = '$name'"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    // Select Trainer By Id
    fun searchTrainerById(id: Int): Cursor {
        val query = "SELECT " +
                TABLE_ROW_TRAINER_ID + "," +
                TABLE_ROW_TRAINER_FIRST_NAME + "," +
                TABLE_ROW_TRAINER_LAST_NAME +
                " from " +
                TABLE_TRAINER + " WHERE " +
                TABLE_ROW_TRAINER_ID + "=" + id + ";"
        Log.i("searchTrainerById() = ", query)
        return db.rawQuery(query, null)
    }

    // Insert Stretch
    fun insertStretch(name: String, duration: String, date: String, trainerId: String) {
        val query = "INSERT INTO $TABLE_STRETCH (" +
                "$TABLE_ROW_STRETCH_NAME, " +
                "$TABLE_ROW_STRETCH_DURATION, " +
                "$TABLE_ROW_DATE, " +
                "$TABLE_ROW_TRAINER_ID" +
                ") " +
                "VALUES (" +
                "'$name', " +
                "'$duration', " +
                "'$date', " +
                "'$trainerId'" +
                ");"
        Log.i("insert() = ", query)
        db.execSQL(query)
    }

    // Delete Stretch
    fun deleteStretch(name: String) {
        val duration = getStretchDuration(name)
        Constants.totalDuration -= duration
        Log.i(Constants.totalDuration.toString(), "DURATION UPDATED")
        Log.i(name, "NAME")
        val query = "DELETE FROM " + TABLE_STRETCH +
                " WHERE " + TABLE_ROW_STRETCH_NAME +
                " = '" + name + "';"
        Log.i("delete()", query)
        db.execSQL(query)

    }

    fun getStretchDuration(packageName: String): Int {
        val query = "SELECT $TABLE_ROW_STRETCH_DURATION FROM $TABLE_STRETCH WHERE $TABLE_ROW_STRETCH_NAME = ?"
        val cursor = db.rawQuery(query, arrayOf(packageName))
        var duration = 0
        if (cursor.moveToFirst()) {
            duration = cursor.getInt(0)
        }
        cursor.close()
        return duration
    }


    // Update Stretch
    fun updateStretch(id: Int, name: String, duration: String, date: String, trainerId: String) {
        // Get the current calories for the exercise
        val currentDuration = getStretchDuration(name)

        // Calculate the difference in calories
        val durationDifference = duration.toInt() - currentDuration

        val query = "UPDATE $TABLE_STRETCH " +
                "SET $TABLE_ROW_STRETCH_NAME='$name'," +
                "$TABLE_ROW_STRETCH_DURATION='$duration'," +
                "$TABLE_ROW_DATE='$date'," +
                "$TABLE_ROW_TRAINER_ID='$trainerId'" +
                " WHERE $TABLE_ROW_STRETCH_ID=$id;"
        Log.i("updateStretch() = ", query)
        db.execSQL(query)

        Constants.totalDuration += durationDifference
    }

    // Select All Stretches
    fun selectAllStretches(): Cursor {
        return db.rawQuery("SELECT * FROM " + TABLE_STRETCH + " ORDER BY " + TABLE_ROW_DATE + " DESC", null)
    }

    // Select Stretch By Name
    fun searchStretchName(name: String): Cursor {
        val query = "SELECT * FROM $TABLE_STRETCH " +
                "WHERE $TABLE_ROW_STRETCH_NAME = '$name'"
        Log.i("searchName() = ", query)
        return db.rawQuery(query, null)
    }

    fun getCaloriesForExercisePackage(packageName: String): Int {
        val query = "SELECT SUM($TABLE_ROW_CALORIES) FROM $TABLE_WORKOUT " +
                "WHERE $TABLE_ROW_EXERCISE LIKE '%$packageName%'"
        val cursor = db.rawQuery(query, null)
        var totalCalories = 0
        if (cursor.moveToFirst()) {
            totalCalories = cursor.getInt(0)
        }
        cursor.close()
        return totalCalories
    }

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

    fun getTotalDurationForTheMonth(stretchId: String): Int {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val currentMonthFormatted = String.format("%02d", currentMonth) // Zero-padding the month if necessary

        val query = "SELECT SUM($TABLE_ROW_STRETCH_DURATION) FROM $TABLE_STRETCH WHERE $TABLE_ROW_STRETCH_ID = '$stretchId' AND SUBSTR($TABLE_ROW_DATE, 6, 2) = '$currentMonthFormatted' AND SUBSTR($TABLE_ROW_DATE, 1, 4) = '$currentYear'"
        val cursor = db.rawQuery(query, null)
        var totalDuration = 0
        if (cursor.moveToFirst()) {
            totalDuration = cursor.getInt(0)
        }
        cursor.close()
        return totalDuration
    }

    fun getExerciseId(exerciseName: String): Int {
        val query = "SELECT $TABLE_ROW_EXERCISE_ID FROM $TABLE_WORKOUT WHERE $TABLE_ROW_EXERCISE = ?"
        val cursor = db.rawQuery(query, arrayOf(exerciseName))
        var exerciseId = -1 // Default value if exercise ID is not found
        if (cursor.moveToFirst()) {
            exerciseId = cursor.getInt(0)
        }
        cursor.close()
        return exerciseId
    }

    fun getStretchId(stretchName: String): Int {
        val query = "SELECT $TABLE_ROW_STRETCH_ID FROM $TABLE_STRETCH WHERE $TABLE_ROW_STRETCH_NAME = ?"
        val cursor = db.rawQuery(query, arrayOf(stretchName))
        var stretchId = -1 // Default value if stretch ID is not found
        if (cursor.moveToFirst()) {
            stretchId = cursor.getInt(0)
        }
        cursor.close()
        return stretchId
    }

    fun getTrainerNameForStretch(stretchName: String): String {
        val stretchId = getStretchId(stretchName)

        if (stretchId != -1) {
            // Retrieve the trainer ID for the stretch using the stretch ID
            val trainerId = getTrainerIdForStretch(stretchId)
            // Retrieve the trainer name using the trainer ID
            val trainerName = getTrainerNameById(trainerId)

            return trainerName ?: "Unknown Trainer"
        }

        return "Unknown Stretch"
    }

    fun getTrainerNameForExercise(exerciseName: String): String {
        val exerciseId = getExerciseId(exerciseName)

        if (exerciseId != -1) {
            // Retrieve the trainer ID for the exercise using the exercise ID
            val trainerId = getTrainerIdForExercise(exerciseId)
            // Retrieve the trainer name using the trainer ID
            val trainerName = getTrainerNameById(trainerId)

            return trainerName ?: "Unknown Trainer"
        }

        return "Unknown Exercise"
    }

    fun getStretchNameForExercise(exerciseName: String): String {
        val exerciseId = getExerciseId(exerciseName)

        if (exerciseId != -1) {
            // Retrieve the trainer ID for the exercise using the exercise ID
            val stretchId = getStretchIdForExercise(exerciseId)
            // Retrieve the trainer name using the trainer ID
            val stretchName = getStretchNameById(stretchId)

            return stretchName ?: "Unknown Trainer"
        }

        return "Unknown Exercise"
    }

    private fun getStretchNameById(stretchId: Int): String {
        val query = "SELECT $TABLE_ROW_STRETCH_NAME FROM $TABLE_STRETCH WHERE $TABLE_ROW_STRETCH_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(stretchId.toString()))
        var stretchName = "" // Default value if stretch name is not found
        if (cursor.moveToFirst()) {
            stretchName = cursor.getString(0)
        }
        cursor.close()
        return stretchName
    }

    private fun getStretchIdForExercise(exerciseId: Int): Int {
        val query = "SELECT $TABLE_ROW_STRETCH_ID FROM $TABLE_WORKOUT WHERE $TABLE_ROW_EXERCISE_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(exerciseId.toString()))
        var stretchId = -1 // Default value if stretch ID is not found
        if (cursor.moveToFirst()) {
            stretchId = cursor.getInt(0)
        }
        cursor.close()
        return stretchId
    }


    @SuppressLint("Range")
    private fun getTrainerNameById(trainerId: Int): String? {
        val query = "SELECT $TABLE_ROW_TRAINER_FIRST_NAME, $TABLE_ROW_TRAINER_LAST_NAME FROM $TABLE_TRAINER WHERE $TABLE_ROW_TRAINER_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(trainerId.toString()))
        var trainerName: String? = null
        if (cursor.moveToFirst()) {
            val firstName = cursor.getString(cursor.getColumnIndex(TABLE_ROW_TRAINER_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(TABLE_ROW_TRAINER_LAST_NAME))
            trainerName = "$firstName $lastName"
        }
        cursor.close()
        return trainerName
    }

    private fun getTrainerIdForStretch(stretchId: Int): Int {
        val query = "SELECT $TABLE_ROW_TRAINER_ID FROM $TABLE_STRETCH WHERE $TABLE_ROW_STRETCH_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(stretchId.toString()))
        var trainerId = -1 // Default value if trainer ID is not found
        if (cursor.moveToFirst()) {
            trainerId = cursor.getInt(0)
        }
        cursor.close()
        return trainerId
    }

    private fun getTrainerIdForExercise(exerciseId: Int): Int {
        val query = "SELECT $TABLE_ROW_TRAINER_ID FROM $TABLE_WORKOUT WHERE $TABLE_ROW_EXERCISE_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(exerciseId.toString()))
        var trainerId = -1 // Default value if trainer ID is not found
        if (cursor.moveToFirst()) {
            trainerId = cursor.getInt(0)
        }
        cursor.close()
        return trainerId
    }

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

    fun getTotalStretchDurationForPastMnoth(): Int {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1) // Subtract 1 month from the current date

        val lastMonthYear = calendar.get(Calendar.YEAR)
        val lastMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val lastMonthFormatted = String.format("%02d", lastMonth) // Zero-padding the month if necessary

        val query = "SELECT SUM($TABLE_ROW_STRETCH_DURATION) FROM $TABLE_STRETCH WHERE SUBSTR($TABLE_ROW_STRETCH_DATE, 6, 2) = '$lastMonthFormatted' AND SUBSTR($TABLE_ROW_STRETCH_DATE, 1, 4) = '$lastMonthYear'"
        val cursor = db.rawQuery(query, null)
        var totalDuration = 0
        if (cursor.moveToFirst()) {
            totalDuration = cursor.getInt(0)
        }
        cursor.close()
        return totalDuration
    }

    @SuppressLint("Range")
    fun getExercisesForTheMonth(): List<String> {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val currentMonthFormatted = String.format("%02d", currentMonth) // Zero-padding the month if necessary

        val query = "SELECT $TABLE_ROW_EXERCISE FROM $TABLE_WORKOUT WHERE SUBSTR($TABLE_ROW_DATE, 6, 2) = '$currentMonthFormatted' AND SUBSTR($TABLE_ROW_DATE, 1, 4) = '$currentYear'"
        val cursor = db.rawQuery(query, null)

        val exercises = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val exercise = cursor.getString(cursor.getColumnIndex(TABLE_ROW_EXERCISE))
                exercises.add(exercise)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return exercises
    }

    @SuppressLint("Range")
    fun getExercisesForPastMonth(): List<String> {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1) // Subtracting 1 month from the current date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val monthFormatted = String.format("%02d", month) // Zero-padding the month if necessary

        val query = "SELECT $TABLE_ROW_EXERCISE FROM $TABLE_WORKOUT WHERE SUBSTR($TABLE_ROW_DATE, 6, 2) = '$monthFormatted' AND SUBSTR($TABLE_ROW_DATE, 1, 4) = '$year'"
        val cursor = db.rawQuery(query, null)

        val exercises = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val exercise = cursor.getString(cursor.getColumnIndex(TABLE_ROW_EXERCISE))
                exercises.add(exercise)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return exercises
    }

    @SuppressLint("Range")
    fun getStretchesForTheMonth(): List<String> {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val currentMonthFormatted = String.format("%02d", currentMonth) // Zero-padding the month if necessary

        val query = "SELECT $TABLE_ROW_STRETCH_NAME FROM $TABLE_STRETCH WHERE SUBSTR($TABLE_ROW_STRETCH_DATE, 6, 2) = '$currentMonthFormatted' AND SUBSTR($TABLE_ROW_STRETCH_DATE, 1, 4) = '$currentYear'"
        val cursor = db.rawQuery(query, null)

        val stretches = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val stretch = cursor.getString(cursor.getColumnIndex(TABLE_ROW_STRETCH_NAME))
                stretches.add(stretch)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return stretches
    }

    @SuppressLint("Range")
    fun getStretchesForPastMonth(): List<String> {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1) // Subtracting 1 month from the current date

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Adding 1 since Calendar.MONTH is zero-based

        val monthFormatted = String.format("%02d", month) // Zero-padding the month if necessary

        val query = "SELECT $TABLE_ROW_STRETCH_NAME FROM $TABLE_STRETCH WHERE SUBSTR($TABLE_ROW_STRETCH_DATE, 6, 2) = '$monthFormatted' AND SUBSTR($TABLE_ROW_STRETCH_DATE, 1, 4) = '$year'"
        val cursor = db.rawQuery(query, null)

        val stretches = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val stretch = cursor.getString(cursor.getColumnIndex(TABLE_ROW_STRETCH_NAME))
                stretches.add(stretch)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return stretches
    }


    // Select Stretch By Id
    fun searchStretchById(id: Int): Cursor {
        val query = "SELECT " +
                TABLE_ROW_STRETCH_ID + "," +
                TABLE_ROW_STRETCH_NAME + "," +
                TABLE_ROW_STRETCH_DURATION + "," +
                TABLE_ROW_DATE + "," +
                TABLE_ROW_TRAINER_ID +
                " from " +
                TABLE_STRETCH + " WHERE " +
                TABLE_ROW_STRETCH_ID + "=" + id + ";"
        Log.i("searchStretchById() = ", query)
        return db.rawQuery(query, null)
    }

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
            val trainersTable = ("CREATE TABLE " + TABLE_TRAINER + " (" +
                    TABLE_ROW_TRAINER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    TABLE_ROW_TRAINER_FIRST_NAME + " TEXT NOT NULL, " +
                    TABLE_ROW_TRAINER_LAST_NAME + " TEXT NOT NULL" +
                    ");")
            db.execSQL(trainersTable)
            // Create a table for stretches
            val stretchesTable = ("CREATE TABLE " + TABLE_STRETCH + " (" +
                    TABLE_ROW_STRETCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    TABLE_ROW_STRETCH_NAME + " TEXT NOT NULL, " +
                    TABLE_ROW_STRETCH_DURATION + " INTEGER NOT NULL, " +
                    TABLE_ROW_STRETCH_DATE + " TEXT NOT NULL, " +
                    TABLE_ROW_TRAINER_ID + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(" + TABLE_ROW_TRAINER_ID + ") REFERENCES " + TABLE_TRAINER + "(" + TABLE_ROW_TRAINER_ID + ")" +
                    ");")
            db.execSQL(stretchesTable)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        }
    }

}
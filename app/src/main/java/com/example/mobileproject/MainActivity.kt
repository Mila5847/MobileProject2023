package com.example.mobileproject

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.mobileproject.DataManager

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.insert -> {
                setContentView(R.layout.activity_insert)
                var insertTrainerButton:Button = findViewById<Button>(R.id.insertTrainerBtn)
                var insertExerciseButton:Button = findViewById<Button>(R.id.insertExerciseBtn)
                var insertStretchButton:Button = findViewById<Button>(R.id.insertStretchBtn)

                insertTrainerButton.setOnClickListener{
                    var fragTrainerInsert = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragTrainerInsert == null) {
                        fragTrainerInsert = FragInsertTrainer()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragTrainerInsert)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragTrainerInsert = FragInsertTrainer()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragTrainerInsert).commit()
                    }

                }

                insertExerciseButton.setOnClickListener{
                    var fragExerciseInsert = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragExerciseInsert == null) {
                        fragExerciseInsert = FragInsertExercise()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragExerciseInsert)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragExerciseInsert = FragInsertExercise()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragExerciseInsert).commit()
                    }
                }

                insertStretchButton.setOnClickListener{
                    var fragStretchInsert = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragStretchInsert == null) {
                        fragStretchInsert = FragInsertStretch()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragStretchInsert)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragStretchInsert = FragInsertStretch()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragStretchInsert).commit()
                    }
                }
            }

            R.id.display -> {
                setContentView(R.layout.activity_display)

                var displayTrainersButtons: Button = findViewById<Button>(R.id.displayTrainersBtn)
                var displayExercisesButtons: Button = findViewById<Button>(R.id.displayExercisesBtn)
                var displayStretchesButtons: Button = findViewById<Button>(R.id.displayStretchesBtn)

                displayTrainersButtons.setOnClickListener {
                    var fragTrainerDisplay = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragTrainerDisplay == null) {
                        fragTrainerDisplay = FragDisplayTrainers()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragTrainerDisplay)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragTrainerDisplay = FragDisplayTrainers()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragTrainerDisplay).commit()
                    }
                }

                displayExercisesButtons.setOnClickListener {
                    var fragExerciseDisplay = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragExerciseDisplay == null) {
                        fragExerciseDisplay = FragDisplayExercises()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragExerciseDisplay)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragExerciseDisplay = FragDisplayExercises()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragExerciseDisplay).commit()
                    }
                }

                displayStretchesButtons.setOnClickListener {
                    var fragStretchDisplay = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragStretchDisplay == null) {
                        fragStretchDisplay = FragDisplayStretches()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragStretchDisplay)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragStretchDisplay = FragDisplayStretches()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragStretchDisplay).commit()
                    }
                }
            }
            R.id.update -> {
                setContentView(R.layout.activity_update)

                var updateTrainer: Button = findViewById<Button>(R.id.updateTrainerBtn)
                var updateExercise: Button = findViewById<Button>(R.id.updateExerciseBtn)
                var updateStretch: Button = findViewById<Button>(R.id.updateStretchBtn)

                updateTrainer.setOnClickListener {
                    var fragTrainerUpdate = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragTrainerUpdate == null) {
                        fragTrainerUpdate = FragUpdateTrainer()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragTrainerUpdate)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragTrainerUpdate = FragUpdateTrainer()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragTrainerUpdate).commit()
                    }
                }

                updateExercise.setOnClickListener {
                    var fragExerciseUpdate = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragExerciseUpdate == null) {
                        fragExerciseUpdate = FragUpdateExercise()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragExerciseUpdate)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragExerciseUpdate = FragUpdateExercise()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragExerciseUpdate).commit()
                    }
                }

                updateStretch.setOnClickListener {
                    var fragStretchUpdate = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragStretchUpdate == null) {
                        fragStretchUpdate = FragUpdateStretch()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragStretchUpdate)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragStretchUpdate = FragUpdateStretch()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragStretchUpdate).commit()
                    }
                }

            }
            R.id.delete -> {
                setContentView(R.layout.activity_delete)

                var deleteTrainer: Button = findViewById<Button>(R.id.deleteTrainerBtn)
                var deleteExercise: Button = findViewById<Button>(R.id.deleteExerciseBtn)
                var deleteStretch: Button = findViewById<Button>(R.id.deleteStretchBtn)

                deleteTrainer.setOnClickListener {
                    var fragTrainerDelete = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragTrainerDelete == null) {
                        fragTrainerDelete = FragDeleteTrainer()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragTrainerDelete)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragTrainerDelete = FragDeleteTrainer()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragTrainerDelete).commit()
                    }
                }

                deleteExercise.setOnClickListener {
                    var fragTrainerDelete1 = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragTrainerDelete1 == null) {
                        fragTrainerDelete1 = FragDeleteExercise()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragTrainerDelete1)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragTrainerDelete1 = FragDeleteExercise()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragTrainerDelete1).commit()
                    }
                }

                deleteStretch.setOnClickListener {
                    var fragStretchDelete = supportFragmentManager
                        .findFragmentById(R.id.fragmentHolder)
                    // Check the fragment has not already been initialized
                    if (fragStretchDelete == null) {
                        fragStretchDelete = FragDeleteStretch()
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragmentHolder, fragStretchDelete)
                            .commit()
                    }
                    // Initialize the fragment based on our FragInsert() and add
                    else {
                        // Initialize the fragment based on our FragInsert() and replace
                        fragStretchDelete = FragDeleteStretch()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentHolder, fragStretchDelete).commit()
                    }
                }

            }
            R.id.monthlyPlan -> {
                setContentView(R.layout.activity_monthlyplan)
                val dataManager = DataManager(this)
                val tvPlan =  findViewById<TextView>(R.id.tvPlan)
                val pastPlan = findViewById<TextView>(R.id.tvPastPlan)
                val tvMonthlyExercises = findViewById<TextView>(R.id.tvMonthlyExercises)
                val tvMonthlyStretches = findViewById<TextView>(R.id.tvMonthlyStretches)
                val tvMonthlyPastExercises = findViewById<TextView>(R.id.tvMonthlyPastExercises)
                val tvMonthlyPastStretches = findViewById<TextView>(R.id.tvMonthlyPastStretches)
                val tvLayout = findViewById<LinearLayout>(R.id.planLayout)


                tvLayout.removeAllViews() // Clear any existing checkboxes

                val exercises = dataManager.getExercisesForTheMonth()
                val stretches = dataManager.getStretchesForTheMonth()

                val pastExercises = dataManager.getExercisesForPastMonth()
                val pastStretches = dataManager.getStretchesForPastMonth()

                tvLayout.addView(tvPlan)


                tvLayout.addView(tvMonthlyStretches)
                for (stretch in stretches) {
                    val checkBox = CheckBox(this)

                    // Get the trainer name for the exercise
                    val trainerName = dataManager.getTrainerNameForStretch(stretch)
                    val exerciseWithTrainerName = "$stretch (Trainer: $trainerName)"

                    checkBox.text = exerciseWithTrainerName
                    // Restore the checkbox state from SharedPreferences
                    val isChecked = sharedPreferences.getBoolean(stretch, false)
                    checkBox.isChecked = isChecked

                    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                        sharedPreferences.edit().putBoolean(stretch, isChecked).apply()
                        // Perform any action when the checkbox state changes
                        if (isChecked) {
                            val stretchId = dataManager.getStretchId(stretch)
                            val duration = dataManager.getTotalDurationForTheMonth(stretchId.toString())
                            Constants.totalDuration += duration
                            Log.i(Constants.totalDuration.toString(), "DURATION")
                        } else {
                            val stretchId = dataManager.getStretchId(stretch)
                            val duration = dataManager.getTotalDurationForTheMonth(stretchId.toString())
                            Constants.totalDuration -= duration
                            if(Constants.totalDuration < 0){
                                Constants.totalDuration = 0
                            }
                            Log.i(Constants.totalDuration.toString(), "DURATION")
                        }
                    }

                    tvLayout.addView(checkBox) // Add the checkbox to the LinearLayout
                }

                tvLayout.addView(tvMonthlyExercises)
                for (exercise in exercises) {

                    val checkBox = CheckBox(this)

                    // Get the trainer name for the exercise
                    val trainerName = dataManager.getTrainerNameForExercise(exercise)
                    val stretchName = dataManager.getStretchNameForExercise(exercise)
                    val exerciseWithTrainerName = "$exercise (Trainer: $trainerName) with suggested stretch: $stretchName"

                    checkBox.text = exerciseWithTrainerName

                    // Restore the checkbox state from SharedPreferences
                    val isChecked = sharedPreferences.getBoolean(exercise, false)
                    checkBox.isChecked = isChecked

                    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                        sharedPreferences.edit().putBoolean(exercise, isChecked).apply()
                        // Perform any action when the checkbox state changes
                        if (isChecked) {
                            val exerciseId = dataManager.getExerciseId(exercise)
                            val calories = dataManager.getTotalCaloriesBurnedForTheMonth(exerciseId.toString())
                            Constants.totalCalories += calories
                            Log.i(Constants.totalCalories.toString(), "CALORIES")
                        } else {
                            val exerciseId = dataManager.getExerciseId(exercise)
                            val calories = dataManager.getTotalCaloriesBurnedForTheMonth(exerciseId.toString())
                            Constants.totalCalories -= calories
                            if(Constants.totalCalories < 0){
                                Constants.totalCalories = 0
                            }
                            Log.i(Constants.totalCalories.toString(), "CALORIES")
                        }
                    }

                    tvLayout.addView(checkBox)
                }

                tvLayout.addView(pastPlan)
                tvLayout.addView(tvMonthlyPastStretches)
                for(pastStretch in pastStretches){
                    val tvPastStretches = TextView(this)
                    val trainerName = dataManager.getTrainerNameForStretch(pastStretch)
                    val exerciseWithTrainerName = "$pastStretch (Trainer: $trainerName)"
                    tvPastStretches.text = exerciseWithTrainerName
                    tvLayout.addView(tvPastStretches)
                }
                tvLayout.addView(tvMonthlyPastExercises)
                for(pastExercise in pastExercises){
                    val tvPastExercises = TextView(this)
                    val trainerName = dataManager.getTrainerNameForExercise(pastExercise)
                    val exerciseWithTrainerName = "$pastExercise (Trainer: $trainerName)"
                    tvPastExercises.text = exerciseWithTrainerName
                    tvLayout.addView(tvPastExercises)
                }
            }
            R.id.summary -> {
                setContentView(R.layout.activity_summary)
                val dataManager = DataManager(this)

                val tvTotalCaloriesPresentMonth = findViewById<TextView>(R.id.tvTotalCaloriesPresentMonth)
                val tvTotalCaloriesPastMonths = findViewById<TextView>(R.id.tvTotalCaloriesPastMonths)

                val tvTotalDurationPresentMonth = findViewById<TextView>(R.id.tvTotalDurationPresentMonth)
                val tvTotalDurationPastMonths = findViewById<TextView>(R.id.tvTotalDurationPastMonths)

                val checkboxCaloriesPresentMonth = findViewById<CheckBox>(R.id.checkboxCaloriesPresentMonth)
                val checkboxCaloriesPastMonths = findViewById<CheckBox>(R.id.checkboxCaloriesPastMonths)

                val checkboxDurationPresentMonth = findViewById<CheckBox>(R.id.checkboxDurationPresentMonth)
                val checkboxDurationPastMonths = findViewById<CheckBox>(R.id.checkboxDurationPastMonths)

                checkboxCaloriesPresentMonth.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        val totalCaloriesBurned = Constants.totalCalories.toString()
                        tvTotalCaloriesPresentMonth.text = "Total Calories Burned: $totalCaloriesBurned calories"
                    } else {
                        // Clear the total calories burned
                        tvTotalCaloriesPresentMonth.text = ""
                    }
                }

                checkboxCaloriesPastMonths.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        // Calculate and display total calories burned
                        val totalCaloriesBurned = dataManager.getTotalCaloriesBurnedUpForThePastMonth()
                        tvTotalCaloriesPastMonths.text = "Total Calories Burned: $totalCaloriesBurned calories"
                    } else {
                        // Clear the total calories burned
                        tvTotalCaloriesPastMonths.text = ""
                    }
                }

                checkboxDurationPresentMonth.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                       val totalStretchDuration = Constants.totalDuration.toString()
                        tvTotalDurationPresentMonth.text = "Total Stretch Duration: $totalStretchDuration minutes"
                    } else {
                        // Clear the total stretch duration
                        tvTotalDurationPresentMonth.text = ""
                    }
                }

                checkboxDurationPastMonths.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        // Calculate and display total stretch duration
                        val totalStretchDuration = dataManager.getTotalStretchDurationForPastMnoth()
                        tvTotalDurationPastMonths.text = "Total Stretch Duration: $totalStretchDuration minutes"
                    } else {
                        // Clear the total stretch duration
                        tvTotalDurationPastMonths.text = ""
                    }
                }

                val radioButtonYes = findViewById<RadioButton>(R.id.radioButtonYes)
                val radioButtonNo = findViewById<RadioButton>(R.id.radioButtonNo)
                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.visibility = View.GONE

                radioButtonYes.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        imageView.visibility = View.VISIBLE // Show the image
                    } else {
                        imageView.visibility = View.GONE // Hide the image
                    }
                }

                radioButtonNo.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        imageView.visibility = View.GONE // Hide the image
                    }
                }

            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
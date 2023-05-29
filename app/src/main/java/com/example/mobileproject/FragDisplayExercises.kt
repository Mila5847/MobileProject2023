package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class FragDisplayExercises : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_display_exercises, container, false)
        val btnSearchExercise = view.findViewById<View>(R.id.btnSearchExercise) as Button
        val editSearchExercise = view.findViewById<View>(R.id.editTextSearchExercises) as EditText
        val textResultsExercises = view.findViewById<View>(R.id.textResultsExercises) as TextView
        val radioAllExercises = view.findViewById<RadioButton>(R.id.radioAllExercises)
        val radioSearchExercise = view.findViewById<RadioButton>(R.id.radioSearchExercises)
        val laySearchExercise = view.findViewById<LinearLayout>(R.id.searchHolderExercises)
        val dm = DataManager(requireActivity())

        radioAllExercises.setOnClickListener{
            if(radioAllExercises.isChecked){
                laySearchExercise.visibility = View.GONE
            }
        }
        radioSearchExercise.setOnClickListener{
            if(radioSearchExercise.isChecked){
                laySearchExercise.visibility = View.VISIBLE
            }
        }
        btnSearchExercise.setOnClickListener {
            val c = dm.searchExerciseName(editSearchExercise.text.toString())
            if(c.count > 0) {
                c.moveToNext()
                textResultsExercises.text = " ${c.getString(0)} - ${c.getString(1)} Sets: ${c.getString(2)} Reps: ${c.getString(3)} Weight: ${c.getString(4)} Calories: ${c.getString(5)} Date: ${c.getString(6)} Trainer id: ${c.getString(7)} Stretch id: ${c.getString(8)}\n"
            }
        }
        updateExercisesList(view)
        return view
    }
    fun updateExercisesList(view: View) {
        val dm = DataManager(requireActivity())
        val textResults = view.findViewById<TextView>(R.id.textResultsExercises)

        val c = dm.selectAllExercises()
        var list = ""
        while (c.moveToNext()) {
            list += " ${c.getString(0)} - ${c.getString(1)} Sets: ${c.getString(2)} Reps: ${c.getString(3)} Weight: ${c.getString(4)} Calories: ${c.getString(5)} Date: ${c.getString(6)} Trainer id: ${c.getString(7)} Stretch id: ${c.getString(8)}\n"
        }
        textResults.text = list
    }

}
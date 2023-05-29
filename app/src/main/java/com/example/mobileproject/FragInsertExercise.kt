package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FragInsertExercise : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_insert_exercise, container, false)
        val dm = DataManager(requireActivity())
        val btnInsertExercise = view.findViewById<View>(R.id.btnInsertExercise) as Button
        val editExercise = view.findViewById<View>(R.id.editExercise) as EditText
        val editSets = view.findViewById<View>(R.id.editSets) as EditText
        val editReps = view.findViewById<View>(R.id.editReps) as EditText
        val editWeight = view.findViewById<View>(R.id.editWeight) as EditText
        val calories = view.findViewById<View>(R.id.editCalories) as EditText
        val editDate = view.findViewById<View>(R.id.editExerciseDate) as EditText
        val trainerIdExercise = view.findViewById<View>(R.id.editExerciseTrainerId) as EditText
        val stretchIdExercise = view.findViewById<View>(R.id.editExerciseStretchId) as EditText
        btnInsertExercise.setOnClickListener {
            dm.insertExercise(
                editExercise.text.toString(),
                editSets.text.toString(),
                editReps.text.toString(),
                editWeight.text.toString(),
                calories.text.toString(),
                editDate.text.toString(),
                trainerIdExercise.text.toString(),
                stretchIdExercise.text.toString()
            )
            editExercise.text = null
            editSets.text = null
            editReps.text = null
            editWeight.text = null
            calories.text = null
            editDate.text = null
            trainerIdExercise.text = null
            stretchIdExercise.text = null
            Toast.makeText(activity, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
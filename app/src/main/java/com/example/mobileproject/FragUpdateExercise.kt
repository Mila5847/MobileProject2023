package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class FragUpdateExercise : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dm = DataManager (requireActivity())
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_update_exercise, container, false)

        val editIDExercise = view.findViewById<View>(R.id.editUpdateExerciseID) as EditText
        val btnUpdateExercise = view.findViewById<View>(R.id.btnUpdateExercise) as Button

        upDateListExercise(view)

        //After entering the ID it retrieves the details for edit btnUpdate.setOnClickListener(
        btnUpdateExercise.setOnClickListener {
            var tid = 0
            val editExercise = view.findViewById<View>(R.id.editExercise) as EditText
            val editSets = view.findViewById<View>(R.id.editSets) as EditText
            val editReps = view.findViewById<View>(R.id.editReps) as EditText
            val editWeight = view.findViewById<View>(R.id.editWeight) as EditText
            val calories = view.findViewById<View>(R.id.editCalories) as EditText
            val editDate = view.findViewById<View>(R.id.editExerciseDate) as EditText
            val trainerIdExercise = view.findViewById<View>(R.id.editExerciseTrainerId) as EditText
            val stretchIdExercise = view.findViewById<View>(R.id.editExerciseStretchId) as EditText

            if (btnUpdateExercise.text == "Edit") {
                tid = editIDExercise.text.toString().toInt()
                val c = dm.searchExerciseById(tid)
                //Make sure a result was found before using the Cursor
                if (c.count > 0) {
                    c.moveToNext()
                    btnUpdateExercise.text = "Update" //ready for update after edit
                    // it retrieves the details for edit
                    editExercise.setText("${c.getString(1)}", TextView.BufferType.EDITABLE)
                    editSets.setText("${c.getString(2)}", TextView.BufferType.EDITABLE)
                    editReps.setText("${c.getString(3)}", TextView.BufferType.EDITABLE)
                    editWeight.setText("${c.getString(4)}", TextView.BufferType.EDITABLE)
                    calories.setText("${c.getString(5)}", TextView.BufferType.EDITABLE)
                    editDate.setText("${c.getString(6)}", TextView.BufferType.EDITABLE)
                    trainerIdExercise.setText("${c.getString(7)}", TextView.BufferType.EDITABLE)
                    stretchIdExercise.setText("${c.getString(8)}", TextView.BufferType.EDITABLE)
                }
                Toast.makeText(activity, "Data ready for edit !!", Toast.LENGTH_LONG).show()
            } else { //update the record in the Database
                tid = editIDExercise.text.toString().toInt()
                val tExercise = editExercise.text.toString()
                val tSets = editSets.text.toString()
                val tReps = editReps.text.toString()
                val tWeight = editWeight.text.toString()
                val tcalories = calories.text.toString()
                val tDate = editDate.text.toString()
                val tTrainerId = trainerIdExercise.text.toString()
                val tStretchId = stretchIdExercise.text.toString()
                dm.updateExercise(tid, tExercise, tSets, tReps, tWeight, tcalories, tDate, tTrainerId, tStretchId)

                btnUpdateExercise.text = "Edit"
                editExercise.text = null
                editSets.text = null
                editReps.text = null
                editWeight.text = null
                calories.text = null
                editDate.text = null
                trainerIdExercise.text = null
                stretchIdExercise.text = null

                Toast.makeText(activity, "Data edited successfully !!", Toast.LENGTH_LONG).show()
                upDateListExercise(view)
            }
        }

        return view
    }

    fun upDateListExercise(view: View?) {
        val dn = DataManager(requireActivity())
        val txtDisplayUpd = view?.findViewById<TextView>(R.id.txtDispUpdateExercises)
        val c = dn.selectAllExercises()
        var list = ""
        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            // Add the results to the String with a little formatting
            list += " ${c.getString(0)} - ${c.getString(1)} Sets: ${c.getString(2)} Reps: ${c.getString(3)} Weight: ${c.getString(4)} Calories: ${c.getString(5)} Date: ${c.getString(6)} Trainer id: ${c.getString(7)} Stretch id: ${c.getString(8)}\n"
        }
        txtDisplayUpd?.text = list
    }

}
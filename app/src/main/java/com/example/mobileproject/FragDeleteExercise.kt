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

class FragDeleteExercise : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_frag_delete_exercise, container, false)
        // Database and UI code goes here
        val dm = DataManager (requireActivity())
        val editDeleteExercise = view.findViewById(R.id.editDeleteExercise) as EditText
        val btnDeleteExercise = view.findViewById<Button>(R.id.btnDeleteExercise)
        upDateListExercise(view)

        btnDeleteExercise.setOnClickListener {
            dm.deleteExercise(editDeleteExercise.text.toString())
            editDeleteExercise.text = null
            Toast.makeText(activity, "Data deleted successfully !!", Toast.LENGTH_LONG).show()
            upDateListExercise(view)
        }
        return view
    }

    fun upDateListExercise(view: View?) {
        val dm = DataManager(requireActivity())
        val txtDisplayDel = view?.findViewById<TextView>(R.id.txtDispDeleteExercise)
        val c = dm.selectAllExercises()
        var list = ""
        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            list += " ${c.getString(0)} - ${c.getString(1)} Sets: ${c.getString(2)} Reps: ${c.getString(3)} Weight: ${c.getString(4)} Calories: ${c.getString(5)} Date: ${c.getString(6)} Trainer id: ${c.getString(7)} Stretch id: ${c.getString(8)}\n"
        }
        txtDisplayDel?.text = list
    }
}
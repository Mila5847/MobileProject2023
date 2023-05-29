package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FragInsertTrainer : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_insert_trainer, container, false)

        val dm = DataManager(requireActivity())

        val btnInsertTrainer = view.findViewById<View>(R.id.btnInsertTrainer) as Button
        val editTrainerFName = view.findViewById<View>(R.id.editTrainerFName) as EditText
        val editTrainerLName = view.findViewById<View>(R.id.editTrainerLName) as EditText

        btnInsertTrainer.setOnClickListener {
            dm.insertTrainer(
                editTrainerFName.text.toString(),
                editTrainerLName.text.toString(),
            )
            editTrainerFName.text = null
            editTrainerLName.text = null
            Toast.makeText(activity, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
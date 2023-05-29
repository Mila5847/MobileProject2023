package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FragInsertStretch : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_insert_stretch, container, false)

        val dm = DataManager(requireActivity())

        val btnInsertStretch = view.findViewById<View>(R.id.btnInsertStretch) as Button
        val editStretch = view.findViewById<View>(R.id.editStretch) as EditText
        val editDuration = view.findViewById<View>(R.id.editDuration) as EditText
        val editStretchDate = view.findViewById<View>(R.id.editStretchDate) as EditText
        val trainerIdStretch = view.findViewById<View>(R.id.editStretchTrainerId) as EditText

        btnInsertStretch.setOnClickListener {
            dm.insertStretch(
                editStretch.text.toString(),
                editDuration.text.toString(),
                editStretchDate.text.toString(),
                trainerIdStretch.text.toString(),
            )
            editStretch.text = null
            editDuration.text = null
            editStretchDate.text = null
            trainerIdStretch.text = null
            Toast.makeText(activity, "Data inserted successfully", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}
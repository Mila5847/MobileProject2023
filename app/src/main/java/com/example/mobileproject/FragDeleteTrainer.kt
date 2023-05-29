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


class FragDeleteTrainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_frag_delete_trainer, container, false)
        // Database and UI code goes here
        val dm = DataManager(requireActivity())

        val editDeleteTrainer = view.findViewById(R.id.editDeleteTrainer) as EditText
        val btnDeleteTrainer = view.findViewById<Button>(R.id.btnDeleteTrainer)
        val txtDisplayDelTrainer = view.findViewById<TextView>(R.id.txtDispDeleteTrainer)
        upDateListTrainer(view)


        btnDeleteTrainer.setOnClickListener {
            dm.deleteTrainer(editDeleteTrainer.text.toString())
            editDeleteTrainer.text = null
            Toast.makeText(activity, "Data deleted successfully !!", Toast.LENGTH_LONG).show()
            upDateListTrainer(view)
        }
        return view
    }

    fun upDateListTrainer(view: View?) {
        val dm = DataManager(requireActivity())
        val txtDisplayDel = view?.findViewById<TextView>(R.id.txtDispDeleteTrainer)
        val c = dm.selectAllTrainers()
        var list = ""
        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            list += "${c.getString(0)} - ${c.getString(1)} ${c.getString(2)}\n"
        }
        txtDisplayDel?.text = list
    }

}
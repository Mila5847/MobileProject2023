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

class FragUpdateTrainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dm = DataManager (requireActivity())
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_update_trainer, container, false)

        val editIDTrainer = view.findViewById<View>(R.id.editUpdateIDTrainer) as EditText
        val btnUpdateTrainer = view.findViewById<View>(R.id.btnUpdateTrainer) as Button

        upDateListTrainer(view)

        btnUpdateTrainer.setOnClickListener {
            var tid = 0
            val editFirstName = view.findViewById<View>(R.id.editTrainerFName) as EditText
            val editLastName = view.findViewById<View>(R.id.editTrainerLName) as EditText
            if (btnUpdateTrainer.text == "Edit") {
                tid = editIDTrainer.text.toString().toInt()
                val c = dm.searchTrainerById(tid)
                //Make sure a result was found before using the Cursor
                if (c.count > 0) {
                    c.moveToNext()
                    btnUpdateTrainer.text = "Update" //ready for update after edit
                    // it retrieves the details for edit
                    editFirstName.setText("${c.getString(1)}", TextView.BufferType.EDITABLE)
                    editLastName.setText("${c.getString(2)}", TextView.BufferType.EDITABLE)
                }
                Toast.makeText(activity, "Data ready for edit !!", Toast.LENGTH_LONG).show()
            } else { //update the record in the Database
                tid = editIDTrainer.text.toString().toInt()
                val tFirstName = editFirstName.text.toString()
                val tLastName = editLastName.text.toString()
                dm.updateTrainer(tid, tFirstName, tLastName)
                btnUpdateTrainer.text = "Edit"
                editFirstName.text = null
                editLastName.text = null
                Toast.makeText(activity, "Data edited successfully !!", Toast.LENGTH_LONG).show()
                upDateListTrainer(view)
            }
        }
        return view
    }

    fun upDateListTrainer(view: View?) {
        val dn = DataManager(requireActivity())
        val txtDisplayUpd = view?.findViewById<TextView>(R.id.txtDispUpdateTrainers)
        val c = dn.selectAllTrainers()
        var list = ""
        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            // Add the results to the String with a little formatting
            list += " ${c.getString(0)} - ${c.getString(1)} ${c.getString(2)}\n"
        }
        txtDisplayUpd?.text = list
    }


}
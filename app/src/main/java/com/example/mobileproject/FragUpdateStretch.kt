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

class FragUpdateStretch : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dm = DataManager (requireActivity())
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_update_stretch, container, false)

        val editIDStretch = view.findViewById<View>(R.id.editUpdateIDStretch) as EditText
        val btnUpdateStretch = view.findViewById<View>(R.id.btnUpdateStretch) as Button

        updateListStretches(view)

        btnUpdateStretch.setOnClickListener {
            var tid = 0
            val editStretchName = view.findViewById<View>(R.id.editStretch) as EditText
            val editStretchDuration = view.findViewById<View>(R.id.editDuration) as EditText
            val editStretchDate = view.findViewById<View>(R.id.editStretchDate) as EditText
            val editStretchTrainerId =
                view.findViewById<View>(R.id.editStretchTrainerId) as EditText
            if (btnUpdateStretch.text == "Edit") {
                tid = editIDStretch.text.toString().toInt()
                val c = dm.searchStretchById(tid)
                //Make sure a result was found before using the Cursor
                if (c.count > 0) {
                    c.moveToNext()
                    btnUpdateStretch.text = "Update" //ready for update after edit
                    // it retrieves the details for edit
                    editStretchName.setText("${c.getString(1)}", TextView.BufferType.EDITABLE)
                    editStretchDuration.setText("${c.getString(2)}", TextView.BufferType.EDITABLE)
                    editStretchDate.setText("${c.getString(3)}", TextView.BufferType.EDITABLE)
                    editStretchTrainerId.setText("${c.getString(4)}", TextView.BufferType.EDITABLE)
                }
                Toast.makeText(activity, "Data ready for edit !!", Toast.LENGTH_LONG).show()
            } else { //update the record in the Database
                tid = editIDStretch.text.toString().toInt()
                val tStretchName = editStretchName.text.toString()
                val tStretchDuration = editStretchDuration.text.toString()
                val tStretchDate = editStretchDate.text.toString()
                val tStretchTrainerId = editStretchTrainerId.text.toString()
                dm.updateStretch(
                    tid,
                    tStretchName,
                    tStretchDuration,
                    tStretchDate,
                    tStretchTrainerId
                )
                btnUpdateStretch.text = "Edit"
                editStretchName.text = null
                editStretchDuration.text = null
                editStretchDate.text = null
                editStretchTrainerId.text = null
                Toast.makeText(activity, "Data edited successfully !!", Toast.LENGTH_LONG).show()
                updateListStretches(view)
            }
        }

        return view
    }

    fun updateListStretches(view: View?) {
        val dn = DataManager(requireActivity())
        val txtDisplayUpd = view?.findViewById<TextView>(R.id.txtDispUpdateStretches)
        val c = dn.selectAllStretches()
        var list = ""
        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            // Add the results to the String with a little formatting
            list += " ${c.getString(0)} - ${c.getString(1)} Duration: ${c.getString(2)} Date: ${c.getString(3)} Trainer id: ${c.getString(4)}\n"
        }
        txtDisplayUpd?.text = list
    }
}
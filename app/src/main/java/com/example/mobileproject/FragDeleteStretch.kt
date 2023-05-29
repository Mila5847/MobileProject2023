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

class FragDeleteStretch : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_frag_delete_stretch, container, false)
        // Database and UI code goes here
        val dm = DataManager (requireActivity())

        val editDeleteStretch = view.findViewById(R.id.editDeleteStretch) as EditText
        val btnDeleteStretch = view.findViewById<Button>(R.id.btnDeleteStretch)
        val txtDisplayDelStretch = view.findViewById<TextView> (R.id.txtDispDeleteStretch)
        upDateListStretch(view)

        btnDeleteStretch.setOnClickListener {
            dm.deleteStretch(editDeleteStretch.text.toString())
            editDeleteStretch.text = null
            Toast.makeText(activity, "Data deleted successfully !!", Toast.LENGTH_LONG).show()
            upDateListStretch(view)
        }
        return view
    }

    fun upDateListStretch(view: View?) {
        val dm = DataManager(requireActivity())
        val txtDisplayDel = view?.findViewById<TextView>(R.id.txtDispDeleteStretch)
        val c = dm.selectAllStretches()
        var list = ""
        // Loop through the results in the Cursor
        while (c.moveToNext()) {
            list += " ${c.getString(0)} - ${c.getString(1)} Duration: ${c.getString(2)} Date: ${c.getString(3)} Trainer id: ${c.getString(4)}\n"
        }
        txtDisplayDel?.text = list
    }
}
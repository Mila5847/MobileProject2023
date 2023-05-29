package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class FragDisplayStretches : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_display_stretches, container, false)

        val btnSearchStretch = view.findViewById<View>(R.id.btnSearchStretch) as Button
        val editSearchStretch = view.findViewById<View>(R.id.editTextSearchStretches) as EditText
        val textResultsStretch = view.findViewById<View>(R.id.textResultsStretches) as TextView
        val radioGroupStretch = view.findViewById<RadioGroup>(R.id.radGrpDisplayStretches)
        val radioAllStretches = view.findViewById<RadioButton>(R.id.radioAllStretches)
        val radioSearchStretch = view.findViewById<RadioButton>(R.id.radioSearchStretches)
        val laySearchStretch = view.findViewById<LinearLayout>(R.id.searchHolderStretches)

        val dm = DataManager(requireActivity())

        radioAllStretches.setOnClickListener{
            if(radioAllStretches.isChecked){
                laySearchStretch.visibility = View.GONE
            }
        }
        radioSearchStretch.setOnClickListener{
            if(radioSearchStretch.isChecked){
                laySearchStretch.visibility = View.VISIBLE
            }
        }
        btnSearchStretch.setOnClickListener {
            val c = dm.searchStretchName(editSearchStretch.text.toString())
            if(c.count > 0) {
                c.moveToNext()
                textResultsStretch.text = " ${c.getString(0)} - ${c.getString(1)} Duration: ${c.getString(2)} Date: ${c.getString(3)} Trainer id: ${c.getString(4)}\n"
            }
        }

        // Update stretches list
        updateStretchesList(view)

        return view
    }

    fun updateStretchesList(view: View) {
        val dm = DataManager(requireActivity())
        val textResults = view.findViewById<TextView>(R.id.textResultsStretches)

        val c = dm.selectAllStretches()
        var list = ""
        while (c.moveToNext()) {
            list += " ${c.getString(0)} - ${c.getString(1)} Duration: ${c.getString(2)} Date: ${c.getString(3)} Trainer id: ${c.getString(4)}\n"
        }
        textResults.text = list
    }
}
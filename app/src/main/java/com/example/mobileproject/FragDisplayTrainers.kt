package com.example.mobileproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class FragDisplayTrainers : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_display_trainers, container, false)

        val btnSearchTrainer = view.findViewById<View>(R.id.btnSearchTrainer) as Button
        val editSearchTrainer = view.findViewById<View>(R.id.editTextSearchTrainer) as EditText
        val textResultsTrainer = view.findViewById<View>(R.id.textResultsTrainers) as TextView
        val radioGroupTrainer = view.findViewById<RadioGroup>(R.id.radGrpDisplayTrainers)
        val radioAllTrainers = view.findViewById<RadioButton>(R.id.radioAllTrainers)
        val radioSearchTrainer = view.findViewById<RadioButton>(R.id.radioSearchTrainer)
        val laySearchTrainer = view.findViewById<LinearLayout>(R.id.searchHolderTrainers)

        val dm = DataManager(requireActivity())

        radioAllTrainers.setOnClickListener{
            if(radioAllTrainers.isChecked){
                laySearchTrainer.visibility = View.GONE
            }
        }
        radioSearchTrainer.setOnClickListener{
            if(radioSearchTrainer.isChecked){
                laySearchTrainer.visibility = View.VISIBLE
            }
        }
        btnSearchTrainer.setOnClickListener {
            val c = dm.searchTrainerName(editSearchTrainer.text.toString())
            if(c.count > 0) {
                c.moveToNext()
                textResultsTrainer.text = " ${c.getString(0)} - ${c.getString(1)} ${c.getString(2)} \n"
            }
        }

        // Update trainers list
        updateTrainersList(view)

        return view
    }

    fun updateTrainersList(view: View) {
        val dm = DataManager(requireActivity())
        val textResults = view.findViewById<TextView>(R.id.textResultsTrainers)

        val c = dm.selectAllTrainers()
        var list = ""
        while (c.moveToNext()) {
            list += " ${c.getString(0)} - ${c.getString(1)} ${c.getString(2)}\n"
        }
        textResults.text = list
    }

}
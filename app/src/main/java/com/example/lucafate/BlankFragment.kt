package com.example.lucafate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_blank, container, false)

        val blankButton = view.findViewById<Button>(R.id.blankButton)
        blankButton.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_homeFragment)
        }

        return view
    }

}
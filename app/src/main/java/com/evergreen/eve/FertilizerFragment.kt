package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class FertilizerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fertilizer, container, false)

        val listView: ListView = view.findViewById(R.id.listView)

        val items = arrayListOf("U 709", "T 750", "T 200", "T 65")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, _, position, _ ->
            val fertilizer = parent.getItemAtPosition(position) as String
            Toast.makeText(requireContext(), "Clicked: $fertilizer", Toast.LENGTH_SHORT).show()
        }

        val applyFertilizerButton: Button = view.findViewById(R.id.btnApplyfertilizer)
        applyFertilizerButton.setOnClickListener {
            val intent = Intent(requireContext(), FertilizerFragment::class.java)
            startActivity(intent)
        }

        return view
    }

}
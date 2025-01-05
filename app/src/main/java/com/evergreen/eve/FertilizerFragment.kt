package com.evergreen.eve

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
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

        val fetilizerInput:EditText = view.findViewById(R.id.txtfertilizersName)
        val quantityInput:EditText = view.findViewById(R.id.txtQuantityAmount)
        val applyButton = view.findViewById<Button>(R.id.btnApplyfertilizer)

        listView.setOnItemClickListener { parent, _, position, _ ->
            val fertilizer = parent.getItemAtPosition(position) as String
            Toast.makeText(requireContext(), "Clicked: $fertilizer", Toast.LENGTH_SHORT).show()
        }


        applyButton.setOnClickListener {
            val nameFertilizer = fetilizerInput.text.toString()
            val quantity = quantityInput.text.toString().toIntOrNull() ?: 0

            val priceFetilizer = 12500

            val totalAmount = priceFetilizer * quantity

            val message = """
                Fertilizer Name: $nameFertilizer
                Quantity: $quantity
                Total Amount: $totalAmount
            """.trimIndent()

            showDialog("Fertilizer Details", message)
        }

        return view
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}
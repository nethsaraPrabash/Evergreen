package com.evergreen.eve

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class TeaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tea, container, false)


        val quantity400gInput: EditText = view.findViewById(R.id.quantity_400g)
        val quantity1kgInput: EditText = view.findViewById(R.id.quantity_1kg)
        val purchaseButton: Button = view.findViewById(R.id.btnPurchase)

        purchaseButton.setOnClickListener {
            val quantity400g = quantity400gInput.text.toString().toIntOrNull() ?: 0
            val quantity1kg = quantity1kgInput.text.toString().toIntOrNull() ?: 0

            val price400g = 650
            val price1kg = 1300


            val total400g = quantity400g * price400g
            val total1kg = quantity1kg * price1kg
            val grandTotal = total400g + total1kg


            val message = """
                400g  Total: Rs.$total400g
                1kg  Total: Rs.$total1kg
                ---------------------------
                 Total: Rs.$grandTotal
            """.trimIndent()

            showDialog("Order Summary", message)
        }

        return view
    }


    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}

package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels


class PaymentsFragment : Fragment() {

    private lateinit var txtTotalAmount: TextView
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_payments, container, false)


        txtTotalAmount = view.findViewById(R.id.txtTotalAmount)

        sharedViewModel.totalAmount.observe(viewLifecycleOwner) { total ->
            val price = total * 200 // Perform the multiplication here
            txtTotalAmount.text = "$price"
        }

        val applyPaymentButton: Button = view.findViewById(R.id.btnApplyAdvance)
        applyPaymentButton.setOnClickListener {
            val intent = Intent(requireContext(), ApplyPaymentActivity::class.java)
            startActivity(intent)
        }
        return view


    }

    private fun updateTotalAmount(total: Long) {
        txtTotalAmount.text = total.toString()
    }

}
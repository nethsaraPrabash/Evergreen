package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class PaymentsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_payments, container, false)

        val applyPaymentButton: Button = view.findViewById(R.id.btnApplyAdvance)
            applyPaymentButton.setOnClickListener {
            val intent = Intent(requireContext(), ApplyPaymentActivity::class.java)
            startActivity(intent)
        }
        return view
        return inflater.inflate(R.layout.fragment_payments, container, false)

    }

}
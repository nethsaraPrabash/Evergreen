package com.evergreen.eve

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast


class NotificationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view = inflater.inflate(R.layout.fragment_notification, container, false)

        val listView: ListView = view.findViewById(R.id.notification_list)

        val  notifications = listOf("notification 1", "notification 2", "notification 3", "notification 4", "notification 5")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, notifications)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position) as String
            Toast.makeText(requireContext(), "Clicked: $item", Toast.LENGTH_SHORT).show()
        }



        return view

    }
}
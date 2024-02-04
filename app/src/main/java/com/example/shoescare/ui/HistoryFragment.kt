package com.example.shoescare.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoescare.R
import com.example.shoescare.adapter.HistoryAdapter
import com.example.shoescare.databinding.FragmentHistoryBinding
import com.example.shoescare.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.shoescare.model.History

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference
    private lateinit var valueEventListener: ValueEventListener
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://shoescare-5ba2a-default-rtdb.asia-southeast1.firebasedatabase.app/")
        ref = database.getReference("payment")

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)

        adapter = HistoryAdapter(requireContext())
        binding.rvHistory.adapter = adapter

        // Fetch and display mata pelajaran from Firebase
        fetchHistory()

    }

    private fun fetchHistory() {
        // ValueEventListener to update the adapter when data changes
        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("HistoryFragment", "Data changed. Count: ${snapshot.childrenCount}")

                val historyList = mutableListOf<History>()

                for (dataSnapshot in snapshot.children) {
                    val history = dataSnapshot.getValue(History::class.java)
                    history?.let {
                        historyList.add(it)
                    }
                }
                adapter.setData(historyList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error if necessary
            }
        }

        // Attach the listener to the database reference
        ref.addValueEventListener(valueEventListener)

        // ... (your existing code)
    }


}
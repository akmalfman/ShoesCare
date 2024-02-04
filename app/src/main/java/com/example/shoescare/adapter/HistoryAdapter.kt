package com.example.shoescare.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.shoescare.databinding.ItemHistoryBinding
import com.example.shoescare.model.History
import com.google.firebase.database.FirebaseDatabase

class HistoryAdapter(private val context: Context) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyList: MutableList<History> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historyList[position]
        holder.binding.tvName.text = history.name
        holder.binding.tvPhone.text = history.phone
        holder.binding.tvQuantity.text = history.quantity
        holder.binding.tvTotal.text = history.total

        Log.e("peminajamList", "onBindViewHolder: $history", )


    }


    fun setData(updatedList: List<History>) {
        historyList.clear()
        historyList.addAll(updatedList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return historyList.size
    }

    inner class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)
}

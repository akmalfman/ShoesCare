package com.example.shoescare.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoescare.R
import com.example.shoescare.databinding.FragmentHomeBinding
import com.example.shoescare.databinding.FragmentTipsBinding
import com.google.firebase.auth.FirebaseAuth

class TipsFragment : Fragment() {
    private lateinit var binding: FragmentTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvTips.setOnClickListener {
            val detailTipsFragment = DetailTipsFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, detailTipsFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
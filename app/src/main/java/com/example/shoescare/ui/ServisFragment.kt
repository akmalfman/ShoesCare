package com.example.shoescare.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoescare.R
import com.example.shoescare.databinding.FragmentHomeBinding
import com.example.shoescare.databinding.FragmentServisBinding
import com.google.firebase.auth.FirebaseAuth

class ServisFragment : Fragment() {

    private lateinit var binding: FragmentServisBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        binding.cvServis1.setOnClickListener {
            val detailServisFragment = DetailServisFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, detailServisFragment)
                .addToBackStack(null)
                .commit()
        }
    }

}
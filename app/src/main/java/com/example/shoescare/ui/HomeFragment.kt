package com.example.shoescare.ui

import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoescare.LoginActivity
import com.example.shoescare.MapsActivity
import com.example.shoescare.R
import com.example.shoescare.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        binding.cardView4.setOnClickListener {
            val tipsFragment = TipsFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, tipsFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cardView2.setOnClickListener {
            val servisFragment = ServisFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, servisFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.tvAlltips.setOnClickListener {
            val tipsFragment = TipsFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, tipsFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cvTips1.setOnClickListener {
            val detailTipsFragment = DetailTipsFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, detailTipsFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cvServis1.setOnClickListener {
            val detailServisFragment = DetailServisFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, detailServisFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.tvAllservis.setOnClickListener {
            val servisFragment = ServisFragment()

            // Ganti fragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.navhost, servisFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.cardView1.setOnClickListener {
            // Redirect to your login activity
            val intent = Intent(requireContext(), MapsActivity::class.java)
            startActivity(intent)

            // Finish the current activity to prevent user from navigating back to it after logout
            requireActivity().finish()
        }

        updateUserName()
    }
    private fun updateUserName() {
        mAuth.currentUser?.let { currentUser ->
            val displayName = currentUser.displayName

            binding.tvName.text = displayName ?: currentUser.email ?: "Pengguna belum login"
        } ?: run {
            binding.tvName.text = "Pengguna belum login"
        }
    }
}
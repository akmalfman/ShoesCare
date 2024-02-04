package com.example.shoescare.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoescare.LoginActivity
import com.example.shoescare.R
import com.example.shoescare.adapter.HistoryAdapter
import com.example.shoescare.databinding.FragmentHistoryBinding
import com.example.shoescare.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        updateUserName()

        binding.cvLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                // User clicked Yes, perform logout
                logout()
            }
            .setNegativeButton("No") { dialog, _ ->
                // User clicked No, dismiss the dialog
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    private fun logout() {
        // Perform logout action here, for example, sign out from Firebase Auth
        FirebaseAuth.getInstance().signOut()

        // Redirect to your login activity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)

        // Finish the current activity to prevent user from navigating back to it after logout
        requireActivity().finish()
    }

    private fun updateUserName() {
        auth.currentUser?.let { currentUser ->
            val displayName = currentUser.displayName

            binding.tvName.text = displayName ?: currentUser.email ?: "Pengguna belum login"
        } ?: run {
            binding.tvName.text = "Pengguna belum login"
        }
    }

}
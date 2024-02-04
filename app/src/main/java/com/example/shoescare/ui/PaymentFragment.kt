package com.example.shoescare.ui

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.shoescare.R
import com.example.shoescare.databinding.FragmentPaymentBinding
import com.example.shoescare.databinding.FragmentServiceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var mAuth: FirebaseAuth
    private var totalPrice: Int = 0
    private var totalQuantity: Int = 0
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        // Retrieve total price from arguments bundle
        arguments?.let {
            totalPrice =
                it.getInt("totalPrice", 0) // 0 is the default value if totalPrice is not found
            totalQuantity =
                it.getInt("totalQuantity", 0) // 0 is the default value if totalPrice is not found
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance("https://shoescare-5ba2a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("payment")

        binding.totalPayment.text = totalPrice.toString()
        binding.totalQuantity.text = totalQuantity.toString()

        binding.btnPay.setOnClickListener {
            val name = binding.EdName.text.toString()
            val phone = binding.EdPhone.text.toString()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val quantity = binding.totalQuantity.text.toString()
                val total = binding.totalPayment.text.toString()
                // Proceed with the payment action
                // For now, let's just show a toast message indicating the fields are not empty
                val message = "Name: $name\nPhone: $phone\nQuantity: $quantity\nTotal: $total"

                // Display a toast with the payment details
                // Save the payment details to Firebase database
                val paymentRef = databaseReference.push()
                val paymentDetails = hashMapOf(
                    "name" to name,
                    "phone" to phone,
                    "quantity" to quantity,
                    "total" to total
                )
                paymentRef.setValue(paymentDetails)
                    .addOnSuccessListener {
                        // Payment details saved successfully
                        Toast.makeText(requireContext(), "Payment details saved successfully!", Toast.LENGTH_SHORT).show()

                        // Optionally, navigate back to the home fragment
                        val homeFragment = HomeFragment()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.navhost, homeFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                    .addOnFailureListener { e ->
                        // Failed to save payment details
                        Toast.makeText(requireContext(), "Failed to save payment details: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Show an error message indicating that name and phone are required
                Toast.makeText(requireContext(), "Name and Phone are required!", Toast.LENGTH_SHORT).show()
            }
        }


    }

}
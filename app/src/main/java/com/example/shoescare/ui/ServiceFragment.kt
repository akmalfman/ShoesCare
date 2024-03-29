package com.example.shoescare.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.shoescare.R
import com.example.shoescare.databinding.FragmentServiceBinding
import com.example.shoescare.databinding.FragmentServisBinding
import com.google.firebase.auth.FirebaseAuth

class ServiceFragment : Fragment() {

    private lateinit var binding: FragmentServiceBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()

        binding.cvServis1.setOnClickListener {
            val dialog =
                Dialog(requireContext()) // 'this' refers to the context, replace it with appropriate context if needed
            dialog.setContentView(R.layout.custom_dialog_layout)

            val btnMinus = dialog.findViewById<ImageButton>(R.id.btnMinus)
            val btnPlus = dialog.findViewById<ImageButton>(R.id.btnPlus)
            val tvQuantity = dialog.findViewById<TextView>(R.id.tvQuantity)
            val btnOrder = dialog.findViewById<Button>(R.id.btnOrder)

            var quantity = 1 // Initial quantity
            val price = 150000 // Initial price

            // Function to update total price based on quantity
            fun updateTotalPrice() {
                val totPrice = price * quantity
                btnOrder.text = "Order ($totPrice)" // Update button text with total price
            }

// Set initial quantity text
            tvQuantity.text = quantity.toString()
            updateTotalPrice()

// Set onClickListener for the plus button
            btnPlus.setOnClickListener {
                quantity++
                tvQuantity.text = quantity.toString()
                updateTotalPrice()
            }

// Set onClickListener for the minus button
            btnMinus.setOnClickListener {
                // Check if quantity is greater than 1 before decrementing
                if (quantity > 1) {
                    quantity--
                    tvQuantity.text = quantity.toString()
                    updateTotalPrice()
                }
            }

//            val totPrice = price * quantity

            // Set onClickListener for the order button
            btnOrder.setOnClickListener {
                // Navigate to PaymentFragment with total price
                val paymentFragment = PaymentFragment()
                val bundle = Bundle()
                bundle.putInt("totalPrice", price * quantity)
                bundle.putInt("totalQuantity", quantity)
                paymentFragment.arguments = bundle
                parentFragmentManager.beginTransaction().replace(R.id.navhost, paymentFragment)
                    .commit()

                // Dismiss the dialog
                dialog.dismiss()
            }


            dialog.show()
        }


    }
}
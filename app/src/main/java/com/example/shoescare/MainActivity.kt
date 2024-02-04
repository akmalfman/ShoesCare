package com.example.shoescare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.shoescare.databinding.ActivityMainBinding
import com.example.shoescare.ui.HistoryFragment
import com.example.shoescare.ui.HomeFragment
import com.example.shoescare.ui.ProfileFragment
import com.example.shoescare.ui.ServisFragment
import com.example.shoescare.ui.TipsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replace(HomeFragment())

        if (intent.hasExtra("fragment")) {
            val fragmentTag = intent.getStringExtra("fragment")
            if (fragmentTag == "ProfileFragment") {
                // Navigate to ServisFragment
                val servisFragment = ServisFragment()

                // Ganti fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.navhost, servisFragment)
                    .addToBackStack(null)
                    .commit()
                Log.d("IntentMapsToMain", "harusnya bisa ya")
            }
        } else {
            // Handle the case when no specific fragment is provided
            Log.d("IntentMapsToMain", "gagal cuy")

            // Navigate to default fragment or perform any other action
        }


        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->replace(HomeFragment())
                R.id.nav_tips->replace(TipsFragment())
                R.id.nav_history->replace(HistoryFragment())
                R.id.nav_profile->replace(ProfileFragment())
            }
            true
        }
    }

    private fun replace(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navhost,fragment)
        fragmentTransaction.commit()
    }
}
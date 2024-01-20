package com.example.shoescare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.shoescare.databinding.ActivityMainBinding
import com.example.shoescare.ui.HistoryFragment
import com.example.shoescare.ui.HomeFragment
import com.example.shoescare.ui.ProfileFragment
import com.example.shoescare.ui.TipsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replace(HomeFragment())
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

    private fun replace(fragment : Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navhost,fragment)
        fragmentTransaction.commit()
    }
}
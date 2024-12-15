package com.example.profilecardxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.profilecardxml.ui.ProfileFragment
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileFragment = ProfileFragment()
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, profileFragment).
        addToBackStack(null).commit()
    }
}
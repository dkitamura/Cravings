package com.dkitamura.crave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dkitamura.crave.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

private lateinit var binding: ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
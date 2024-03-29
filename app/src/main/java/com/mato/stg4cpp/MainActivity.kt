package com.mato.stg4cpp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mato.stg4cpp.databinding.ActivityMainBinding

@Suppress("")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rest = Restaurant(1.0f).toJSONObject()
        // Example of a call to a native method
        binding.sampleText.text = rest.toString()
    }
}
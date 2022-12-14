package com.example.hangman_meathead

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hangman_meathead.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PreferencesManager.init(this)

        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)

            val intentMain = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intentMain)

            finish()
        }
    }
}
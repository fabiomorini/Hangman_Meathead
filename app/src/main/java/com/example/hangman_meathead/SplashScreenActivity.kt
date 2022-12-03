package com.example.hangman_meathead

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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

        setContentView(R.layout.activity_splash_screen)

        CoroutineScope(Dispatchers.Default).launch {
            delay(1000)

            val intentMain = Intent(this@SplashScreenActivity,  MainActivity::class.java)
            startActivity(intentMain)

            finish()
        }
    }
}
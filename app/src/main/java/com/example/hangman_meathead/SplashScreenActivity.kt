package com.example.hangman_meathead

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
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

        setContentView(binding.root)

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val audioIsActive = sharedPrefs.getBoolean("audioIsActive", true)
        val username = sharedPrefs.getString("username", null)
        val email = sharedPrefs.getString("email", null)
        val password = sharedPrefs.getString("password", null)

        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)

            val intentMain = Intent(this@SplashScreenActivity,  MainActivity::class.java)
            startActivity(intentMain)

            finish()
        }
    }
}
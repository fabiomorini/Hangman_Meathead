package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityMainMenuBinding
import com.example.hangman_meathead.databinding.ActivitySplashScreenBinding

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.playButton.setOnClickListener{
            val intentMain = Intent(this@MainMenuActivity, GameActivity::class.java)
            startActivity(intentMain)
        }

        binding.scoreButton.setOnClickListener{
            val intentMain = Intent(this@MainMenuActivity, LeaderboardActivity::class.java)
            startActivity(intentMain)
        }

        binding.settingsButton.setOnClickListener{
            binding.settingsMenu.visibility = View.VISIBLE
        }

        //region Settings
        binding.closeSettingsButton.setOnClickListener{
            binding.settingsMenu.visibility = View.INVISIBLE
        }

        binding.audioSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //TODO
            } else {
                //TODO
            }
        }

        binding.exitSettingsButton.setOnClickListener{
            binding.settingsMenu.visibility = View.INVISIBLE
        }
        //endregion Settings
    }
}
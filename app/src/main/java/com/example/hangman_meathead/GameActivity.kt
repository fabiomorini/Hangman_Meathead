package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityGameBinding
import com.example.hangman_meathead.databinding.ActivityLoginBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //region Shared Preferences
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPrefs.edit()
        val username = sharedPrefs.getString("username", null)
        val email = sharedPrefs.getString("email", null)
        val password = sharedPrefs.getString("password", null)
        //endregion Shared Preferences

        //region Game
        binding.pauseButton.setOnClickListener{
            binding.pauseMenu.visibility = View.VISIBLE
        }
        //endregion Game

        //region Pause
        binding.closeButton.setOnClickListener{
            binding.pauseMenu.visibility = View.INVISIBLE
        }

        binding.settingsButton.setOnClickListener{
            binding.pauseMenu.visibility = View.INVISIBLE
            binding.settingsMenu.visibility = View.VISIBLE
        }

        binding.retryButton.setOnClickListener{
            val intentMain = Intent(this@GameActivity, GameActivity::class.java)
            startActivity(intentMain)

            finish()
        }

        binding.exitButton.setOnClickListener{
            val intentMain = Intent(this@GameActivity, MainActivity::class.java)
            startActivity(intentMain)

            finish()
        }
        //endregion Pause

        //region Settings
        binding.closeSettingsButton.setOnClickListener{
            binding.pauseMenu.visibility = View.VISIBLE
            binding.settingsMenu.visibility = View.INVISIBLE
        }

        binding.audioSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editor.putBoolean("audioIsActive", true)
//                binding.audioButton.setImageResource(R.drawable.audioon)
            } else {
                editor.putBoolean("audioIsActive", false)
//                binding.audioButton.setImageResource(R.drawable.audiooff)
            }
        }

        binding.exitSettingsButton.setOnClickListener{
            binding.pauseMenu.visibility = View.VISIBLE
            binding.settingsMenu.visibility = View.INVISIBLE
        }
        //endregion Settings
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
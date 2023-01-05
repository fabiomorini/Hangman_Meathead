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

        binding.audioSwitch.isChecked = PreferencesManager.isSoundActive()
        binding.notificationsSwitch.isChecked = PreferencesManager.isNotificationsActive()

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
            val intentMain = Intent(this@GameActivity, MainMenuActivity::class.java)
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
                PreferencesManager.setSoundActive(true)
            } else {
                PreferencesManager.setSoundActive(false)
            }
        }

        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                PreferencesManager.setNotificationsActive(true)
            } else {
                PreferencesManager.setNotificationsActive(false)
            }
        }

        binding.hintsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.hintsSwitch.isChecked = false
                Toast.makeText(this, "Under construction!", Toast.LENGTH_LONG).show()
            } else {
                binding.hintsSwitch.isChecked = false
                Toast.makeText(this, "Under construction!", Toast.LENGTH_LONG).show()
            }
        }

        binding.exitSettingsButton.setOnClickListener{
            binding.pauseMenu.visibility = View.VISIBLE
            binding.settingsMenu.visibility = View.INVISIBLE
        }
        //endregion Settings
    }

    fun ValidateKey()
    {
        //TODO (Needs implementation)
    }

    fun UpdateCharacter()
    {
        //TODO (Needs implementation)
    }

    fun Timer()
    {
        //TODO (Needs implementation)
    }
}
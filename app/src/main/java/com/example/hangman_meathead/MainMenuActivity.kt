package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityMainMenuBinding
import com.example.hangman_meathead.scores.LeaderboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val dbUser = FirebaseAuth.getInstance().currentUser
        val dbUID = dbUser?.uid.toString()

        val userPreferencesRef = db.collection("user_preferences").document(dbUID)

        userPreferencesRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val username = document.getString("username").toString()
                val soundActive = document.getBoolean("sound_active") ?: true
                val notificationsActive = document.getBoolean("notifications_active") ?: true

                PreferencesManager.setUsername(username)
                PreferencesManager.setSoundActive(soundActive)
                PreferencesManager.setNotificationsActive(notificationsActive)
                Toast.makeText(this, "User: $username, sound: $soundActive, notifications: $notificationsActive", Toast.LENGTH_LONG).show()
            }
        }

        binding.audioSwitch.isChecked = PreferencesManager.isSoundActive()
        binding.notificationsSwitch.isChecked = PreferencesManager.isNotificationsActive()

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
            binding.settingsMenu.visibility = View.INVISIBLE
        }
        //endregion Settings
    }

    override fun onPause() {
        val dbUser = FirebaseAuth.getInstance().currentUser
        val dbUID = dbUser?.uid.toString()

        val userPreferencesRef = db.collection("user_preferences").document(dbUID)

        val data = hashMapOf(
            "username" to PreferencesManager.getUsername(),
            "sound_active" to PreferencesManager.isSoundActive(),
            "notifications_active" to PreferencesManager.isNotificationsActive()
        )
        userPreferencesRef.set(data)

        super.onPause()
    }
}
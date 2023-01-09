package com.example.hangman_meathead

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hangman_meathead.databinding.ActivityMainMenuBinding
import com.example.hangman_meathead.scores.LeaderboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

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

        val currentDate = Calendar.getInstance().time.toString()

        val dbUser = FirebaseAuth.getInstance().currentUser
        val dbUID = dbUser?.uid.toString()

        val userPreferencesRef = db.collection("user_preferences").document(dbUID)

        userPreferencesRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val username = document.getString("username").toString()
                val lastConnection = document.getString("last_connection") ?: currentDate
                val soundActive = document.getBoolean("sound_active") ?: true
                val notificationsActive = document.getBoolean("notifications_active") ?: true

                PreferencesManager.setUsername(username)
                PreferencesManager.setSoundActive(soundActive)
                PreferencesManager.setNotificationsActive(notificationsActive)
                PreferencesManager.setLastConnection(lastConnection)
            }
        }

        // Ahora que ya tenemos disponible la última fecha de acceso del usuario a la app
        // Programa el servicio para que se ejecute cada 24 horas, desde aquí
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ConnectionCheckService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, intent, 0)
        val interval: Long = 24 * 60 * 60 * 1000 // 24 horas en milisegundos
        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            interval,
            interval,
            pendingIntent
        )
        //Hasta aquí

        binding.audioSwitch.isChecked = PreferencesManager.isSoundActive()
        binding.notificationsSwitch.isChecked = PreferencesManager.isNotificationsActive()

        binding.playButton.setOnClickListener {
            val intentMain = Intent(this@MainMenuActivity, GameActivity::class.java)
            startActivity(intentMain)
        }

        binding.scoreButton.setOnClickListener {
            val intentMain = Intent(this@MainMenuActivity, LeaderboardActivity::class.java)
            startActivity(intentMain)
        }

        binding.settingsButton.setOnClickListener {
            binding.settingsMenu.visibility = View.VISIBLE
        }

        //region Settings
        binding.closeSettingsButton.setOnClickListener {
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

        binding.exitSettingsButton.setOnClickListener {
            binding.settingsMenu.visibility = View.INVISIBLE
        }
        //endregion Settings
    }

    override fun onPause() {
        val currentDate = Calendar.getInstance().time.toString()
        val dbUser = FirebaseAuth.getInstance().currentUser
        val dbUID = dbUser?.uid.toString()

        val userPreferencesRef = db.collection("user_preferences").document(dbUID)

        val data = hashMapOf(
            "username" to PreferencesManager.getUsername(),
            "sound_active" to PreferencesManager.isSoundActive(),
            "notifications_active" to PreferencesManager.isNotificationsActive(),
            "last_connection" to currentDate
        )
        userPreferencesRef.set(data)

        PreferencesManager.setLastConnection(currentDate)

        super.onPause()
    }
}
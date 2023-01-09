package com.example.hangman_meathead

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hangman_meathead.databinding.ActivityGameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.audioSwitch.isChecked = PreferencesManager.isSoundActive()
        binding.notificationsSwitch.isChecked = PreferencesManager.isNotificationsActive()

        //region Game
        binding.pauseButton.setOnClickListener {
            binding.pauseMenu.visibility = View.VISIBLE
        }

        nextHangman()

        //endregion Game

        //region Pause
        binding.closeButton.setOnClickListener {
            binding.pauseMenu.visibility = View.INVISIBLE
        }

        binding.settingsButton.setOnClickListener {
            binding.pauseMenu.visibility = View.INVISIBLE
            binding.settingsMenu.visibility = View.VISIBLE
        }

        binding.retryButton.setOnClickListener {
            val intentMain = Intent(this@GameActivity, GameActivity::class.java)
            startActivity(intentMain)

            finish()
        }

        binding.exitButton.setOnClickListener {
            val intentMain = Intent(this@GameActivity, MainMenuActivity::class.java)
            startActivity(intentMain)

            finish()
        }
        //endregion Pause

        //region Settings
        binding.closeSettingsButton.setOnClickListener {
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

        binding.exitSettingsButton.setOnClickListener {
            binding.pauseMenu.visibility = View.VISIBLE
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

    fun nextHangman() {
        val outside = Retrofit.Builder()
            .baseUrl("http://hangman.enti.cat:5002/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val services = outside.create(HangmanAPI::class.java)
        services.getRandomHangman().enqueue(object : Callback<Hangman> {

            override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                val hangman = response.body()
                binding.hangmanText.text = hangman?.hangman ?: "404: Not found";

                Toast.makeText(this@GameActivity, hangman.toString(), Toast.LENGTH_LONG).show()

                val hangmanGame = outside.create(HangmanAPI::class.java)
                val token = hangman?.token.toString()
                hangmanGame.getHangman(token)?.enqueue(object : Callback<Hangman> {

                    override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {

                        Toast.makeText(this@GameActivity, hangman.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<Hangman>, t: Throwable) {

                    }
                })
            }

            override fun onFailure(call: Call<Hangman>, t: Throwable) {

            }
        })
    }


    fun ValidateKey() {
        //TODO (Needs implementation)
    }

    fun UpdateCharacter() {
        //TODO (Needs implementation)
    }

    fun Timer() {
        //TODO (Needs implementation)
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<Hangman>) {

}

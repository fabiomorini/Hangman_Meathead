package com.example.hangman_meathead

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
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

    private lateinit var services: HangmanAPI
    private var hangmanGame: Hangman? = null
    private var currentIncorrectGuesses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.audioSwitch.isChecked = PreferencesManager.isSoundActive()
        binding.notificationsSwitch.isChecked = PreferencesManager.isNotificationsActive()

        //region Game
        nextHangman()

        binding.pauseButton.setOnClickListener {
            binding.pauseMenu.visibility = View.VISIBLE
        }

        //region Letters
        binding.aButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "a").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.bButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "b").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.cButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "c").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.dButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "d").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.eButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "a").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.aButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "e").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.fButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "f").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.gButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "g").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.hButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "h").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.iButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "i").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.jButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "j").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.kButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "k").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.lButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "l").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.mButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "m").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.nButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "n").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.nnButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "ñ").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.oButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "o").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }
        binding.pButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "p").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.qButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "q").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.rButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "r").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.sButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "s").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.tButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "t").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.uButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "u").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.vButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "v").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.wButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "w").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.xButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "x").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.yButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "y").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.zButton.setOnClickListener(){
            hangmanGame?.let { it1 -> services.sendLetter(it1.token, "z").execute() }
            if(ValidateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }
        //endregion Letters
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

    private fun nextHangman() {
        val outside = Retrofit.Builder()
            .baseUrl("http://hangman.enti.cat:5002/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        services = outside.create(HangmanAPI::class.java)
        services.getRandomHangman().enqueue(object : Callback<Hangman> {

            override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                hangmanGame = response.body()
                binding.hangmanText.text = hangmanGame?.hangman ?: "404: Not found"

                hangmanGame?.let {
                    services.getHangmanGame(it.token).enqueue(object : Callback<Hangman> {
                        override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                            // procesa la respuesta aquí
                            hangmanGame = response.body()
                        }

                        override fun onFailure(call: Call<Hangman>, t: Throwable) {
                            // maneja el error aquí
                        }
                    })
                }
            }

            override fun onFailure(call: Call<Hangman>, t: Throwable) {

            }
        })
    }


    fun ValidateKey(): Boolean {
        if(currentIncorrectGuesses < hangmanGame?.incorrectGuesses?.toInt() ?: 5 &&
            hangmanGame?.incorrectGuesses ?: 5 != 5){

            currentIncorrectGuesses++
            return false
        }
        else if(hangmanGame?.incorrectGuesses ?: 5 == 5){
            binding.hangmanText.text = hangmanGame?.solution ?: "404: Not found"
            return false
        }
        else if(currentIncorrectGuesses == hangmanGame?.incorrectGuesses?.toInt() ?: 5 &&
            hangmanGame?.incorrectGuesses ?: 5 != 5){
            binding.hangmanText.text = hangmanGame?.hangman ?: "404: Not found"
            return true
        }
        else return false
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

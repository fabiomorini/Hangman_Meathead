package com.example.hangman_meathead

import android.content.Intent
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
    private var incorrectGuesses = 0
    private var incorrectGuessesCounter = 0

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
            sendLetter("a")
//            if(validateKey()) binding.aButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.aButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.bButton.setOnClickListener(){
            sendLetter("b")
//            if(validateKey()) binding.bButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.bButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.cButton.setOnClickListener(){
            sendLetter("c")
//            if(validateKey()) binding.cButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.cButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.dButton.setOnClickListener(){
            sendLetter("d")
//            if(validateKey()) binding.dButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.dButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.eButton.setOnClickListener(){
            sendLetter("e")
//            if(validateKey()) binding.eButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.eButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.fButton.setOnClickListener(){
            sendLetter("f")
//            if(validateKey()) binding.fButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.fButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.gButton.setOnClickListener(){
            sendLetter("g")
//            if(validateKey()) binding.gButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.gButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.hButton.setOnClickListener(){
            sendLetter("h")
//            if(validateKey()) binding.hButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.hButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.iButton.setOnClickListener(){
            sendLetter("i")
//            if(validateKey()) binding.iButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.iButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.jButton.setOnClickListener(){
            sendLetter("j")
//            if(validateKey()) binding.jButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.jButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.kButton.setOnClickListener(){
            sendLetter("k")
//            if(validateKey()) binding.kButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.kButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.lButton.setOnClickListener(){
            sendLetter("l")
//            if(validateKey()) binding.lButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.lButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.mButton.setOnClickListener(){
            sendLetter("m")
//            if(validateKey()) binding.mButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.mButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.nButton.setOnClickListener(){
            sendLetter("n")
//            if(validateKey()) binding.nButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.nButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.nnButton.setOnClickListener(){
            sendLetter("ñ")
//            if(validateKey()) binding.nnButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.nnButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.oButton.setOnClickListener(){
            sendLetter("o")
//            if(validateKey()) binding.oButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.oButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.pButton.setOnClickListener(){
            sendLetter("p")
//            if(validateKey()) binding.pButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.pButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.qButton.setOnClickListener(){
            sendLetter("q")
//            if(validateKey()) binding.qButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.qButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.rButton.setOnClickListener(){
            sendLetter("r")
//            if(validateKey()) binding.rButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.rButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.sButton.setOnClickListener(){
            sendLetter("s")
//            if(validateKey()) binding.sButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.sButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.tButton.setOnClickListener(){
            sendLetter("t")
//            if(validateKey()) binding.tButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.tButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.uButton.setOnClickListener(){
            sendLetter("u")
//            if(validateKey()) binding.uButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.uButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.vButton.setOnClickListener(){
            sendLetter("v")
//            if(validateKey()) binding.vButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.vButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.wButton.setOnClickListener(){
            sendLetter("w")
//            if(validateKey()) binding.wButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.wButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.xButton.setOnClickListener(){
            sendLetter("x")
//            if(validateKey()) binding.xButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.xButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.yButton.setOnClickListener(){
            sendLetter("y")
//            if(validateKey()) binding.yButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.yButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
        }

        binding.zButton.setOnClickListener(){
            sendLetter("z")
//            if(validateKey()) binding.zButton.setColorFilter(0xFF00FF00.toInt(), PorterDuff.Mode.ADD)
//            else binding.zButton.setColorFilter(0xFFFF0000.toInt(), PorterDuff.Mode.ADD)
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

    private fun sendLetter(letter: String) {
        // hace la llamada a sendLetter aquí
        hangmanGame?.let {
            services.sendLetter(it.token, letter).enqueue(object : Callback<Hangman> {
                override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                    services.getHangmanGame(it.token).enqueue(object : Callback<Hangman> {
                        override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                            // procesa la respuesta aquí
                            hangmanGame = response.body()
                            var letterData = response.body()
                            if (letterData?.incorrectGuesses != null) {
                                incorrectGuesses = letterData!!.incorrectGuesses.toInt()
                                if (incorrectGuesses >= 5) {
                                    binding.hangmanText.text = hangmanGame?.solution ?: "404: Not found"
                                    binding.hangmanText.setTextColor(0xFFFF0000.toInt())
                                }
                                else binding.hangmanText.text = hangmanGame?.hangman ?: "404: Not found"
                            }
                        }

                        override fun onFailure(call: Call<Hangman>, t: Throwable) {
                            // maneja el error aquí
                        }
                    })
                }

                override fun onFailure(call: Call<Hangman>, t: Throwable) {
                    // maneja el error aquí
                }
            })
        }
    }

    private fun validateKey() {

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

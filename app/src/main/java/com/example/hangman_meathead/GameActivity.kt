package com.example.hangman_meathead

import android.app.AlertDialog
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hangman_meathead.databinding.ActivityGameBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameActivity : AppCompatActivity() {
    companion object {
        const val PREFERENCES_COLLECTION = "user_preferences"
    }

    private lateinit var binding: ActivityGameBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var services: HangmanAPI
    private var hangmanGame: Hangman? = null
    private var incorrectAttempts = 0
    private var maxAttempts = 5

    //Admob
    private var mRewardedAd: RewardedAd? = null
    private final var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.audioSwitch.isChecked = PreferencesManager.isSoundActive()
        binding.notificationsSwitch.isChecked = PreferencesManager.isNotificationsActive()

        MobileAds.initialize(this) {}

        var adRequest = AdRequest.Builder().build()
        RewardedAd.load(this,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                mRewardedAd = rewardedAd

                mRewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        mRewardedAd = null
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        // Called when ad fails to show.
                        mRewardedAd = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                    }
                }
            }
        })

        //region Game
        nextHangman()

        binding.pauseButton.setOnClickListener {
            binding.pauseMenu.visibility = View.VISIBLE
        }

        //region Letters
        binding.aButton.setOnClickListener() {
            sendLetter("a", binding.aButton)
        }

        binding.bButton.setOnClickListener() {
            sendLetter("b", binding.bButton)
        }

        binding.cButton.setOnClickListener() {
            sendLetter("c", binding.cButton)
        }

        binding.dButton.setOnClickListener() {
            sendLetter("d", binding.dButton)
        }

        binding.eButton.setOnClickListener() {
            sendLetter("e", binding.eButton)
        }

        binding.fButton.setOnClickListener() {
            sendLetter("f", binding.fButton)
        }

        binding.gButton.setOnClickListener() {
            sendLetter("g", binding.gButton)
        }

        binding.hButton.setOnClickListener() {
            sendLetter("h", binding.hButton)
        }

        binding.iButton.setOnClickListener() {
            sendLetter("i", binding.iButton)
        }

        binding.jButton.setOnClickListener() {
            sendLetter("j", binding.jButton)
        }

        binding.kButton.setOnClickListener() {
            sendLetter("k", binding.kButton)
        }

        binding.lButton.setOnClickListener() {
            sendLetter("l", binding.lButton)
        }

        binding.mButton.setOnClickListener() {
            sendLetter("m", binding.mButton)
        }

        binding.nButton.setOnClickListener() {
            sendLetter("n", binding.nButton)
        }

        binding.nnButton.setOnClickListener() {
            sendLetter("ñ", binding.nnButton)
        }

        binding.oButton.setOnClickListener() {
            sendLetter("o", binding.oButton)
        }

        binding.pButton.setOnClickListener() {
            sendLetter("p", binding.pButton)
        }

        binding.qButton.setOnClickListener() {
            sendLetter("q", binding.qButton)
        }

        binding.rButton.setOnClickListener() {
            sendLetter("r", binding.rButton)
        }

        binding.sButton.setOnClickListener() {
            sendLetter("s", binding.sButton)
        }

        binding.tButton.setOnClickListener() {
            sendLetter("t", binding.tButton)
        }

        binding.uButton.setOnClickListener() {
            sendLetter("u", binding.uButton)
        }

        binding.vButton.setOnClickListener() {
            sendLetter("v", binding.vButton)
        }

        binding.wButton.setOnClickListener() {
            sendLetter("w", binding.wButton)
        }

        binding.xButton.setOnClickListener() {
            sendLetter("x", binding.xButton)
        }

        binding.yButton.setOnClickListener() {
            sendLetter("y", binding.yButton)
        }

        binding.zButton.setOnClickListener() {
            sendLetter("z", binding.zButton)
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
                Toast.makeText(this, R.string.function_unimplemented, Toast.LENGTH_LONG).show()
            } else {
                binding.hintsSwitch.isChecked = false
                Toast.makeText(this, R.string.function_unimplemented, Toast.LENGTH_LONG).show()
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

        val userPreferencesRef = db.collection(PREFERENCES_COLLECTION).document(dbUID)

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
                binding.hangmanText.text = (hangmanGame?.hangman ?: R.string.word_not_found).toString()

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

    private fun sendLetter(letter: String, imageButton: ImageButton) {
        // hace la llamada a sendLetter aquí
        hangmanGame?.let {
            services.sendLetter(it.token, letter).enqueue(object : Callback<Hangman> {
                override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                    services.getHangmanGame(it.token).enqueue(object : Callback<Hangman> {
                        override fun onResponse(call: Call<Hangman>, response: Response<Hangman>) {
                            // procesa la respuesta aquí
                            hangmanGame = response.body()
                            validateKey(response.body(), imageButton)
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

    private fun validateKey(response: Hangman?, imageButton: ImageButton) {
        if (response != null) {
            if (incorrectAttempts < response.incorrectGuesses.toInt()) {
                imageButton.setColorFilter(
                    0xFFFF0000.toInt(),
                    PorterDuff.Mode.ADD
                )
                incorrectAttempts = response.incorrectGuesses.toInt()

                if (incorrectAttempts >= maxAttempts) {
                    showMoreTriesDialog()

                } else binding.hangmanText.text =
                    (hangmanGame?.hangman ?: R.string.word_not_found).toString()
            } else imageButton.setColorFilter(
                0xFF00FF00.toInt(),
                PorterDuff.Mode.ADD
            )
            binding.hangmanText.text =
                (hangmanGame?.hangman ?: R.string.word_not_found).toString()

            if((hangmanGame?.hangman ?: R.string.word_not_found) == (hangmanGame?.solution
                    ?: R.string.word_not_found)
            ){
                //Muestra pantalla de victoria con la palabra correcta
            }
        } else binding.hangmanText.text =
            (hangmanGame?.solution ?: R.string.word_not_found).toString()
    }

    fun UpdateCharacter() {
        //TODO (Needs implementation)
    }

    fun Timer() {
        //TODO (Needs implementation)
    }



    private fun showMoreTriesDialog(){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(R.string.more_tries_question)
        dialog.setPositiveButton(R.string.yes) { _, _ ->
            //Mostrar anuncio
            if (mRewardedAd != null) {
                mRewardedAd?.show(this, OnUserEarnedRewardListener() {
                        fun onUserEarnedReward() {
                            maxAttempts++
                    }
                })
            } else {
            }
        }
        dialog.setNegativeButton(R.string.no){ _, _ ->
            //Mostrar pantalla de "you lose"
        }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }
}

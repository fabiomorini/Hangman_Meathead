package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class SignUpActivity : AppCompatActivity() {
    companion object {
        const val PREFERENCES_COLLECTION = "user_preferences"
    }

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.signUpSignUpButton.setOnClickListener {

            val username = binding.inputName.text.toString()
            val mail = binding.inputSignUpMail.text.toString()
            val password = binding.inputSignUpPassword.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener {
                    val intentMainMenu = Intent(this@SignUpActivity, MainMenuActivity::class.java)
                    startActivity(intentMainMenu)

                    PreferencesManager.setUsername(username)
                    PreferencesManager.setEmail(mail)
                    PreferencesManager.setPassword(password)

                    //Estos datos los guardaremos también en las funciones onPause de
                    //las activity MainMenu y Game, desde aquí
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
                    //Hasta aquí. Literalmente, copiaremos este código

                    Toast.makeText(
                        this,
                        "Registro del usuario ${username} creado con éxito",
                        Toast.LENGTH_LONG
                    ).show()

                    finish()

                }.addOnFailureListener {
                    Toast.makeText(this, R.string.unknown_error, Toast.LENGTH_LONG).show()
                }
        }

        binding.inputSignUpMail.setOnFocusChangeListener { _, hasFocus ->

            if (!hasFocus) {
                val mail = binding.inputSignUpMail.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    binding.inputSignUpMail.error = R.string.email_format_error.toString()
                } else {
                    binding.inputSignUpMail.error = null
                }
            }
        }

        binding.signUpLogInButton.setOnClickListener {
            val intentMain = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intentMain)

            finish()
        }

        binding.countryButton.setOnClickListener {
            Toast.makeText(this, R.string.function_unimplemented, Toast.LENGTH_LONG).show()
        }
    }
}
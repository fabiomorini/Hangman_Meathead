package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    val PREFERENCES_COLLECTION = "user_preferences"

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

                    val dbUser = FirebaseAuth.getInstance().currentUser
                    val dbUID = dbUser?.uid.toString()

                    val userPreferencesRef = db.collection(PREFERENCES_COLLECTION).document(dbUID)

                    val data = hashMapOf(
                        "username" to username,
                        "sound_active" to true,
                        "notifications_active" to true
                    )
                    userPreferencesRef.set(data)

                    Toast.makeText(this, "Registro del usuario ${username} creado con éxito", Toast.LENGTH_LONG).show()

                    finish()

                }.addOnFailureListener {
                    Toast.makeText(this, "Ha ocurrido un problema!", Toast.LENGTH_LONG).show()
                }
        }

        binding.inputSignUpMail.setOnFocusChangeListener { _, hasFocus ->

            if (!hasFocus) {
                val mail = binding.inputSignUpMail.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    binding.inputSignUpMail.error = "Formato de email incorrecto!"
                } else {
                    binding.inputSignUpMail.error = null
                }
            }
        }

        binding.signUpLogInButton.setOnClickListener{
            val intentMain = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intentMain)

            finish()
        }

        binding.countryButton.setOnClickListener{
            Toast.makeText(this, "Under construction!", Toast.LENGTH_LONG).show()
        }
    }
}
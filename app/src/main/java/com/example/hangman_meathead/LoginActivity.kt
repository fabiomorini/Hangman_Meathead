package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Patterns
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.inputMail.text.toString()
            val password = binding.inputPassword.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val intentMain = Intent(this@LoginActivity, MainMenuActivity::class.java)
                startActivity(intentMain)

                PreferencesManager.setEmail(binding.inputMail.text.toString())
                PreferencesManager.setPassword(binding.inputPassword.text.toString())

                finish()

            }.addOnFailureListener {
                Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show()
            }
        }

        binding.inputMail.setOnFocusChangeListener { _, hasFocus ->

            if (!hasFocus) {
                val mail = binding.inputMail.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    binding.inputMail.error = R.string.email_format_error.toString()
                } else {
                    binding.inputMail.error = null
                }
            }
        }

        binding.forgotPasswordButton.setOnClickListener{
            Toast.makeText(this, R.string.function_unimplemented, Toast.LENGTH_LONG).show()
        }

        binding.signUpButton.setOnClickListener{
            val intentMain = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentMain)

            finish()
        }
    }
}
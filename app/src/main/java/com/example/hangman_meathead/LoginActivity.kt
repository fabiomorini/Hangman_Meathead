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

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPrefs.edit()
        val loadedEmail = sharedPrefs.getString("email", null)
        val loadedPassword = sharedPrefs.getString("password", null)

        firebaseAuth = FirebaseAuth.getInstance()

        if(loadedEmail != null && loadedPassword != null){
            firebaseAuth.signInWithEmailAndPassword(loadedEmail, loadedPassword)
            .addOnSuccessListener {
                val intentMain = Intent(this@LoginActivity, MainMenuActivity::class.java)
                startActivity(intentMain)

                finish()

            }.addOnFailureListener {
                Toast.makeText(this, "Login incorrecto!", Toast.LENGTH_LONG).show()
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.inputMail.text.toString()
            val password = binding.inputPassword.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val intentMain = Intent(this@LoginActivity, MainMenuActivity::class.java)
                startActivity(intentMain)

                editor.putString("email", binding.inputMail.text.toString())
                editor.putString("password", binding.inputPassword.text.toString())
                editor.apply()

                finish()

            }.addOnFailureListener {
                Toast.makeText(this, "Login incorrecto!", Toast.LENGTH_LONG).show()
            }
        }

        binding.inputMail.setOnFocusChangeListener { view, hasFocus ->

            if (!hasFocus) {
                val mail = binding.inputMail.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    binding.inputMail.error = "Formato de email incorrecto!"
                } else {
                    binding.inputMail.error = null
                }
            }
        }

        binding.forgotPasswordButton.setOnClickListener{
            Toast.makeText(this, "Under construction!", Toast.LENGTH_LONG).show()
        }

        binding.signUpButton.setOnClickListener{
            val intentMain = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentMain)

            finish()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
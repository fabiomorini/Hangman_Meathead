package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private val CORRECT_MAIL = "a@a.a"
    private val CORRECT_PASSWORD = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener {
            val mail = binding.inputMail.text.toString()
            val password = binding.inputPassword.text.toString()

            firebaseAuth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener {
                val intentMain = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intentMain)

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
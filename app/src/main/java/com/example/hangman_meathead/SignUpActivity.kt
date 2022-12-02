package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpSignUpButton.setOnClickListener {

            val username = binding.inputName.text.toString()
            val mail = binding.inputSignUpMail.text.toString()
            val password = binding.inputSignUpPassword.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener {
                    val intentMain = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(intentMain)

                    finish()

                }.addOnFailureListener {
                    Toast.makeText(this, "Ha ocurrido un problema!", Toast.LENGTH_LONG).show()
                }
        }

        binding.inputSignUpMail.setOnFocusChangeListener { view, hasFocus ->

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
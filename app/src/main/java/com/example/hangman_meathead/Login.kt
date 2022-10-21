package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val CORRECT_USERNAME = "abcd"
    private val CORRECT_PASSWORD = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()

            if (username == CORRECT_USERNAME && password == CORRECT_PASSWORD) {
                //Se hace login
                binding.progressBar.visibility = View.VISIBLE

                CoroutineScope(Dispatchers.Default).launch {
                    delay(3000)

                    val intentMain = Intent(this@Login, MainActivity::class.java)
                    startActivity(intentMain)

                    finish()
                }

            } else {
                Toast.makeText(this, "Login incorrecto!", Toast.LENGTH_LONG).show()
            }
        }

        binding.inputUsername.setOnFocusChangeListener { view, hasFocus ->

            if (!hasFocus) {
                val username = binding.inputUsername.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    binding.inputUsername.error = "Formato de email incorrecto!"
                } else {
                    binding.inputUsername.error = null
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
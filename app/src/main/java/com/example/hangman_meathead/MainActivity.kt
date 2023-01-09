package com.example.hangman_meathead

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hangman_meathead.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val loadedEmail: String = PreferencesManager.getEmail()
        val loadedPassword: String = PreferencesManager.getPassword()

        binding.tapimage.setOnClickListener {
            if (loadedEmail != "" && loadedPassword != "") {
                firebaseAuth.signInWithEmailAndPassword(loadedEmail, loadedPassword)
                    .addOnSuccessListener {
                        val intentMain = Intent(this@MainActivity, MainMenuActivity::class.java)
                        startActivity(intentMain)

                        finish()

                    }.addOnFailureListener {
                        val intentLogin = Intent(this@MainActivity, LoginActivity::class.java)
                        startActivity(intentLogin)

                        finish()
                    }
            } else {
                val intentLogin = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intentLogin)

                finish()
            }
        }
    }
}
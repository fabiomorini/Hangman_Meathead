package com.example.hangman_meathead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hangman_meathead.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.buttonToast.setOnClickListener{
//            Toast.makeText(this, "Hola, soy un Toast", Toast.LENGTH_LONG).show()
//        }

        binding.floatingButton.setOnClickListener{
            Snackbar.make(binding.root, "Hola, soy un Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Undo"){
                    //Code to undo}.show()
                }.show()
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
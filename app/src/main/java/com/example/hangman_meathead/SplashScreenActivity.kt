package com.example.hangman_meathead

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hangman_meathead.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    // Declara constantes para los permisos que necesita la aplicación
    private val REQUEST_FOREGROUND_SERVICE = 1
    private val REQUEST_VIBRATE = 2
    private val REQUEST_RECEIVE_BOOT_COMPLETED = 3
    private val REQUEST_DISABLE_KEYGUARD = 4
    private val REQUEST_WAKE_LOCK = 5

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Comprueba si tienes los permisos necesarios
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.FOREGROUND_SERVICE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Si no los tienes, solicita los permisos
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.FOREGROUND_SERVICE),
                REQUEST_FOREGROUND_SERVICE
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.VIBRATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.VIBRATE),
                REQUEST_VIBRATE
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_BOOT_COMPLETED
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECEIVE_BOOT_COMPLETED),
                REQUEST_RECEIVE_BOOT_COMPLETED
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.DISABLE_KEYGUARD
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.DISABLE_KEYGUARD),
                REQUEST_DISABLE_KEYGUARD
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WAKE_LOCK
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WAKE_LOCK),
                REQUEST_WAKE_LOCK
            )
        }

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PreferencesManager.init(this)

        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)

            val intentMain = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intentMain)

            finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_FOREGROUND_SERVICE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El usuario ha concedido el permiso
                } else {
                    // El usuario ha denegado el permiso
                }
            }
            REQUEST_VIBRATE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El usuario ha concedido el permiso
                } else {
                    // El usuario ha denegado el permiso
                }
            }
            REQUEST_RECEIVE_BOOT_COMPLETED -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El usuario ha concedido el permiso
                } else {
                    // El usuario ha denegado el permiso
                }
            }
            REQUEST_DISABLE_KEYGUARD -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El usuario ha concedido el permiso
                } else {
                    // El usuario ha denegado el permiso
                }
            }
            REQUEST_WAKE_LOCK -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El usuario ha concedido el permiso
                } else {
                    // El usuario ha denegado el permiso
                }
            }
        }
    }
}
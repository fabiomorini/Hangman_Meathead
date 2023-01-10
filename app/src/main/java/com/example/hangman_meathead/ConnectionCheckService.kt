package com.example.hangman_meathead

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ConnectionCheckService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Obtén la fecha actual
        val currentDate = Calendar.getInstance().time

        // Obtén la fecha de la última conexión del usuario de la base de datos de Firebase
        val db = FirebaseFirestore.getInstance()
        val dbUID = FirebaseAuth.getInstance().currentUser?.uid
        val docRef = db.collection("user_preferences").document(dbUID.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val lastConnectionString = document.getString("last_connection").toString()
                    val lastConnection = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
                        .parse(lastConnectionString)

                    // Calcula la diferencia en días entre la fecha actual y la fecha de la última conexión
                    val diff = TimeUnit.MILLISECONDS.toDays(
                        currentDate.time - (lastConnection?.time
                            ?: 0)
                    )
                    if (diff >= 3) { // Si han pasado 3 o más días desde la última conexión
                        // Muestra una notificación al usuario
                        showNotification()
                    }
                } else {
                    // Si no se puede acceder al documento del usuario en Firestore, obtén la fecha de la última conexión del usuario de las preferencias compartidas
                    val sharedPreferences = this@ConnectionCheckService.getSharedPreferences(
                        "mis_preferencias",
                        Context.MODE_PRIVATE
                    )
                    val lastConnectionString = sharedPreferences.getString("last_connection", "")
                    if (lastConnectionString != "") {
                        val lastConnection = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
                        .parse(lastConnectionString)

                        // Calcula la diferencia en días entre la fecha actual y la fecha de la última conexión
                        val diff = TimeUnit.MILLISECONDS.toDays(
                            currentDate.time - (lastConnection?.time ?: 0)
                        )
                        if (diff >= 3) { // Si han pasado 3 o más días desde la última conexión
                            // Muestra una notificación al usuario
                            showNotification()
                        }
                    }
                }
            }

        return START_STICKY
    }

    private fun showNotification() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the notification channel if running on Android 8.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "channel_name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "channel_description"
            notificationManager.createNotificationChannel(channel)
        }

        // Create the notification
        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle("MeatHead")
            .setSmallIcon(R.drawable.asset_45)
            .setContentText("¿Echas de menos los retos de nuestro juego? ¡Ven y demuestra que tu cerebro no es solo decorativo!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        // Create an Intent to start the main activity when the notification is clicked
        val intent = Intent(this, SplashScreenActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationBuilder.setContentIntent(pendingIntent)

        // Show the notification
        try {
            notificationManager.notify(0, notificationBuilder.build())
        } catch (_: Exception) {

        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
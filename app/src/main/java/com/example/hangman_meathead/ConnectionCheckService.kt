package com.example.hangman_meathead

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
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

        //Mostramos la notificación para testearla
        showNotification()

        // Obtén la fecha de la última conexión del usuario de la base de datos de Firebase
        val db = FirebaseFirestore.getInstance()
        val dbUID = FirebaseAuth.getInstance().currentUser?.uid
        val docRef = db.collection("user_preferences").document(dbUID.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val lastConnection = document.getDate("last_connection")
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
                        val lastConnection =
                            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).parse(
                                lastConnectionString
                            )
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

        return Service.START_STICKY
    }

    private fun showNotification() {
        // Crea la notificación
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle("MeatHead")
            .setSmallIcon(R.drawable.asset_45)
            .setContentText("¿Echas de menos los retos de nuestro juego? ¡Ven y demuestra que tu celebro no es solo decorativo!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        // Crea un Intent para iniciar la actividad principal al pulsar la notificación
        val intent = Intent(this, SplashScreenActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        notificationBuilder.setContentIntent(pendingIntent)

        // Muestra la notificación
        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
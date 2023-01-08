package com.example.hangman_meathead

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver {
    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val serviceIntent = Intent(context, ConnectionCheckService::class.java)
            context.startService(serviceIntent)
        }
    }
}
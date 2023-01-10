package com.example.hangman_meathead

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class PreferencesManager {

    companion object {
        private const val PREFERENCE_USERNAME = "username"
        private const val PREFERENCE_EMAIL = "email"
        private const val PREFERENCE_PASSWORD = "password"
        private const val PREFERENCE_SOUND_ACTIVE = "sound_active"
        private const val PREFERENCE_NOTIFICATIONS_ACTIVE = "notifications_active"
        private const val PREFERENCE_LAST_CONNECTION = "last_connection"

        private lateinit var preferences: SharedPreferences

        fun init(context: Context) {
            preferences = context.getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
        }

        fun getUsername(): String {
            return preferences.getString(PREFERENCE_USERNAME, "").toString()
        }

        fun setUsername(username: String) {
            val editor = preferences.edit()
            editor.putString(PREFERENCE_USERNAME, username)
            editor.apply()
        }

        fun getEmail(): String {
            return preferences.getString(PREFERENCE_EMAIL, "").toString()
        }

        fun setEmail(email: String) {
            val editor = preferences.edit()
            editor.putString(PREFERENCE_EMAIL, email)
            editor.apply()
        }

        fun getPassword(): String {
            return preferences.getString(PREFERENCE_PASSWORD, "").toString()
        }

        fun setPassword(password: String) {
            val editor = preferences.edit()
            editor.putString(PREFERENCE_PASSWORD, password)
            editor.apply()
        }

        fun isSoundActive(): Boolean {
            return preferences.getBoolean(PREFERENCE_SOUND_ACTIVE, true)
        }

        fun setSoundActive(active: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean(PREFERENCE_SOUND_ACTIVE, active)
            editor.apply()
        }

        fun isNotificationsActive(): Boolean {
            return preferences.getBoolean(PREFERENCE_NOTIFICATIONS_ACTIVE, true)
        }

        fun setNotificationsActive(active: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean(PREFERENCE_NOTIFICATIONS_ACTIVE, active)
            editor.apply()
        }

        fun getLastConnection(): String {
            return preferences.getString(
                PREFERENCE_LAST_CONNECTION,
                Calendar.getInstance().time.toString()
            ).toString()
        }

        fun setLastConnection(lastConnection: String) {
            val editor = preferences.edit()
            editor.putString(PREFERENCE_LAST_CONNECTION, lastConnection)
            editor.apply()
        }
    }
}
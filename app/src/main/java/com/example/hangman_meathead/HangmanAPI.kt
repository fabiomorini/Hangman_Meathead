package com.example.hangman_meathead

import com.google.firebase.firestore.auth.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface HangmanAPI {

    @GET("new?lang=en&maxTries=5")
    fun getRandomHangman(): Call<Hangman>

    @GET("game?token={token}")
    open fun getHangman(@Path("token") token: String?): Call<Hangman?>?
}
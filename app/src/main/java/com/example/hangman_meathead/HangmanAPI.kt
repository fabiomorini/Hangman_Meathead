package com.example.hangman_meathead

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query


interface HangmanAPI {

    @GET("new?lang=en&maxTries=5")
    fun getRandomHangman(): Call<Hangman>

    @GET("game")
    fun getHangmanGame(@Query("token") token: String): Call<Hangman>

    @PUT("hangman")
    fun sendLetter(@Query("token") token: String, @Query("letter") letter: String): Call<Hangman>
}
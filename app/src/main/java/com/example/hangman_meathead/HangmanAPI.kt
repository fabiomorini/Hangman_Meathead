package com.example.hangman_meathead

import retrofit2.Call
import retrofit2.http.GET

interface HangmanAPI {

    @GET("new?lang=en&maxTries=5")
    fun getRandomWord(): Call<Hangman>
}
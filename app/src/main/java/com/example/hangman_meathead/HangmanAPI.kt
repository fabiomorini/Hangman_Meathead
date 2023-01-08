package com.example.hangman_meathead

import retrofit2.Call
import retrofit2.http.GET

interface HangmanAPI {

    @GET("random.json/")
    fun getRandomWord(): Call<String>
}
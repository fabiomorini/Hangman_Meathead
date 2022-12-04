package com.example.hangman_meathead

import retrofit2.Call
import retrofit2.http.GET

interface ApiQuotes {

    @GET("random.json/")
    fun getRandomQuote(): Call<Quote>
}
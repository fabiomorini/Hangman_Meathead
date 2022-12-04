package com.example.hangman_meathead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangman_meathead.databinding.ActivityQuotesBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextQuoteButton.setOnClickListener(){
            nextQuote()
        }
    }

    fun nextQuote(){
        val outside = Retrofit.Builder()
            .baseUrl("http://quotes.stormconsultancy.co.uk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val services = outside.create(ApiQuotes::class.java)
        services.getRandomQuote().enqueue(object: Callback<Quote> {
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                val quote = response.body()

                binding.quoteTextView.text = quote?.text ?: "Something wrong :("
                binding.authorTextView.text = quote?.author ?: ""

            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                binding.quoteTextView.text = "Something wrong :("
                binding.authorTextView.text = ""
            }
        })
    }
}
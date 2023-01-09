package com.example.hangman_meathead

data class Hangman(
    val language: String,
    val maxTries: Integer?,
    val token: String,
    val solution: String,
    val hangman: String,
    val status: Boolean,
    val incorrectGuesses: Integer)


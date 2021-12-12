package com.example.testtaskforbootcamp.data

import com.example.testtaskforbootcamp.data.network.WordApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Retrofit {

    val kanyeApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .build()
        .create(WordApi::class.java)
    suspend fun getQuote(word:String) = kanyeApi
        .getWord(word)[0]
}
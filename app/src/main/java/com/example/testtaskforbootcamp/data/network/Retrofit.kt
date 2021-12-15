package com.example.testtaskforbootcamp.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Retrofit {

    private val wordApi: WordApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .build()
        .create(WordApi::class.java)

    suspend fun getWord(word: String) = wordApi.getWord(word)[0]

}
package com.example.testtaskforbootcamp.data.network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class Retrofit @Inject constructor(

) {


    private val wordApi: WordApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .build()
        .create(WordApi::class.java)

    suspend fun getWord(word: String) =
        try {

            wordApi.getWord(word)[0]
        } catch (e: Exception) {


            when (e) {
                is SocketTimeoutException, is ConnectException, is UnknownHostException -> {
                    isNoConnection = true
                    isWrongWord = false
                }
                else -> {
                    isWrongWord = true
                }
            }
        }

    companion object {
        var isNoConnection = false
        var isWrongWord = false
    }
}
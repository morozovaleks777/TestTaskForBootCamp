package com.example.testtaskforbootcamp.data.network

import android.app.Application
import android.widget.Toast
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class   Retrofit @Inject constructor(){

    private val wordApi: WordApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
        .build()
        .create(WordApi::class.java)

    suspend fun getWord(word: String) = wordApi.getWord(word)[0]

    suspend fun <T : Any> Call<T>.await(): T {
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                cancel()
            }
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            val invocation = call.request().tag(Invocation::class.java)!!
                            val method = invocation.method()
                            val e = KotlinNullPointerException("Response from " +
                                    method.declaringClass.name +
                                    '.' +
                                    method.name +
                                    " was null but response body type was declared as non-null")
                            continuation.resumeWithException(e)
                        } else {
                            continuation.resume(body)
                        }
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                   continuation.resumeWithException(t)

                }
            })
        }
    }
}
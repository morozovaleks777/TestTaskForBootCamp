package com.example.testtaskforbootcamp.data.network


import com.example.testtaskforbootcamp.domain.WordItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class WordResponse : ArrayList<WordResponse.WordResponseItem>(){
    @JsonClass(generateAdapter = true)
    data class WordResponseItem(
        val itemId:Int=WordItem.UNDEFINED_ID,
        @Json(name = "meanings")
        val meanings: List<Meaning>,
        @Json(name = "phonetics")
        val phonetics: List<Phonetic>,
        @Json(name = "word")
        val word: String=""
    ) {
        @JsonClass(generateAdapter = true)
        data class Meaning(
            @Json(name = "definitions")
            val definitions: List<Definition>,
            @Json(name = "partOfSpeech")
            val partOfSpeech: String=""
        ) {
            @JsonClass(generateAdapter = true)
            data class Definition(
                @Json(name = "definition")
                val definition: String="",
                @Json(name = "example")
                val example: String="",
                @Json(name = "synonyms")
                val synonyms: List<String>
            )
        }
    
        @JsonClass(generateAdapter = true)
        data class Phonetic(
            @Json(name = "audio")
            val audio: String="",
            @Json(name = "text")
            val text: String=""
        )
    }
}
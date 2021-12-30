package com.example.testtaskforbootcamp.domain

data class WordItem(
    var itemId: Int = UNDEFINED_ID,
    val meanings: String,
    val phonetic: String,
    val word: String,

    ) {
    companion object {
        const val UNDEFINED_ID = 0
        var idCounter = 0
    }

}

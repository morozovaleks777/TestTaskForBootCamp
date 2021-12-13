package com.example.testtaskforbootcamp.domain

import javax.inject.Inject

data class WordItem @Inject constructor (
     var itemId: Int = UNDEFINED_ID,
     val meanings: String,
     val phonetic: String,
     val word: String,

    )
    {
        companion object {
        const val UNDEFINED_ID = 0
    }

    }

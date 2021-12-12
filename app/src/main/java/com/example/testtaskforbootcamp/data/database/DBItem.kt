package com.example.testtaskforbootcamp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtaskforbootcamp.domain.WordItem

@Entity(tableName = "wordItem")
data class DBItem(
    @PrimaryKey
    var itemId:Int=WordItem.UNDEFINED_ID,
    val meanings: String,
    val phonetic: String,
    val word: String
)

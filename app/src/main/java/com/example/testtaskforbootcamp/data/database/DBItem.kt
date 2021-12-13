package com.example.testtaskforbootcamp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testtaskforbootcamp.domain.WordItem
import javax.inject.Inject

@Entity(tableName = "wordItem")
data class  DBItem @Inject constructor(
    @PrimaryKey
    var itemId:Int=WordItem.UNDEFINED_ID,
    val meanings: String,
    val phonetic: String,
    val word: String
)

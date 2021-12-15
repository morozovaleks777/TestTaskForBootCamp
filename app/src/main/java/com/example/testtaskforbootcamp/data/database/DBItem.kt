package com.example.testtaskforbootcamp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wordItem")
data class DBItem(
    @PrimaryKey
    val itemId: Int,
    val meanings: String,
    val phonetic: String,
    val word: String
)

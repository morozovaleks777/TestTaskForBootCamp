package com.example.testtaskforbootcamp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WordResponseListDao {

    @Query("SELECT * FROM wordItem")
    fun getWordList(): LiveData<List<DBItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWordItem(wordDBModel: DBItem)

    @Query("SELECT * FROM wordItem WHERE word LIKE :word LIMIT 1")
    suspend fun getWordItem(word: String): DBItem

}

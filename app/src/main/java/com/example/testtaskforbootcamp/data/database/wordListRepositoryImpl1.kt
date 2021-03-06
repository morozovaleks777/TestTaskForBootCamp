package com.example.testtaskforbootcamp.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.testtaskforbootcamp.data.WordListMapper
import com.example.testtaskforbootcamp.domain.WordItem
import com.example.testtaskforbootcamp.domain.WordListRepository

class WordListRepositoryImpl1(application: Application) : WordListRepository {

    private val wordListDao = AppDatabase.getInstance(application).wordListDao()
    private val mapper = WordListMapper()

    override suspend fun getWordItem(word: String): WordItem {
        val dbModel = wordListDao.getWordItem(word)
        return mapper.mapDBItemTOWordItem(dbModel)
    }

    override fun getWordList(): LiveData<List<WordItem>> =
        Transformations.map(wordListDao.getWordList()) {
            mapper.mapListDBModelToListEntity(it)
        }

    override suspend fun addWordItem(wordItem: WordItem) {
        wordItem.itemId = WordItem.idCounter++
        wordListDao.addWordItem(mapper.mapWordItem1ToDbItem(wordItem))

    }


}
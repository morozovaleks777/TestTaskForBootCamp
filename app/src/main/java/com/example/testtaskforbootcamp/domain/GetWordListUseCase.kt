package com.example.testtaskforbootcamp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetWordListUseCase @Inject constructor(private val wordListRepository: WordListRepository) {

    fun getWordList(): LiveData<List<WordItem>> {
        return wordListRepository.getWordList()
    }


}
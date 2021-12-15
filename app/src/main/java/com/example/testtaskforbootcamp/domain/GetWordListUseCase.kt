package com.example.testtaskforbootcamp.domain

import androidx.lifecycle.LiveData

class GetWordListUseCase(private val wordListRepository: WordListRepository) {

    fun getWordList(): LiveData<List<WordItem>> {
        return wordListRepository.getWordList()
    }


}
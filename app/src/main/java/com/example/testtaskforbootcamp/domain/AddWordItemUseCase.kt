package com.example.testtaskforbootcamp.domain

import javax.inject.Inject


class AddWordItemUseCase @Inject constructor
    (private val wordListRepository: WordListRepository) {

    suspend fun addWordItem(wordItem: WordItem) {
        wordListRepository.addWordItem(wordItem)
    }

}
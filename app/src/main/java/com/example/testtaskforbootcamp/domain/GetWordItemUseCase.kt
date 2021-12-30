package com.example.testtaskforbootcamp.domain

import javax.inject.Inject

class GetWordItemUseCase @Inject constructor(private val wordListRepository: WordListRepository) {
    suspend fun getWordItem(word: String): WordItem {
        return wordListRepository.getWordItem(word)
    }
}
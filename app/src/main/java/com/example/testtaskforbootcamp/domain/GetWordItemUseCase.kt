package com.example.testtaskforbootcamp.domain

class GetWordItemUseCase(private  val wordListRepository: WordListRepository) {
   suspend fun getWordItem(word: String): WordItem {
 return wordListRepository.getWordItem(word)
    }
}
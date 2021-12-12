package com.example.testtaskforbootcamp.domain

class AddWordItemUseCase (private  val wordListRepository: WordListRepository){

  suspend  fun addWordItem(wordItem: WordItem){
      wordListRepository.
      addWordItem(wordItem )
    }
}
package com.example.testtaskforbootcamp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskforbootcamp.data.Retrofit
import com.example.testtaskforbootcamp.data.network.WordResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val kanyeRepository = Retrofit()

    val wordLiveData=MutableLiveData<WordResponse.WordResponseItem>()
        init {
        fetchWord("hello")
    }

   fun fetchWord(word:String) {
        viewModelScope.launch(Dispatchers.IO) {
            val wordResponse=kanyeRepository.getQuote(word)
            wordLiveData.postValue(wordResponse)
        }
    }
}
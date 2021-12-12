package com.example.testtaskforbootcamp.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskforbootcamp.data.Retrofit
import com.example.testtaskforbootcamp.data.network.WordResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel :ViewModel() {
    private val kanyeRepository = Retrofit()

    val wordLiveData=MutableLiveData<WordResponse.WordResponseItem>()

    private val _errorInputName=MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _closeScreen=MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen


        init {
        fetchWord("hello")
    }

   fun fetchWord(word:String) {
       val parseWord=parseInputName(word)
       val wordValid=validateInput(parseWord)
if(wordValid) {
    viewModelScope.launch(Dispatchers.IO) {
     try {
            val wordResponse = kanyeRepository.getQuote(word)
            wordLiveData.postValue(wordResponse)
        }catch (exception:Exception) {
            if(exception is HttpException ){

            }
       }

        }

}
    }

    private fun validateInput(inputName:String):Boolean{
        var result=true
        if(inputName.isBlank()) {
            _errorInputName.value=true
            result = false
        }

        return result
    }
    fun resetErrorInputName(){
        _errorInputName.value=false
    }
    private fun parseInputName(inputName: String?):String{
        return inputName?.trim() ?: ""
    }
}
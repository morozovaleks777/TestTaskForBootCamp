package com.example.testtaskforbootcamp.presentation


import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.testtaskforbootcamp.data.WordListMapper
import com.example.testtaskforbootcamp.data.database.WordListRepositoryImpl1
import com.example.testtaskforbootcamp.data.network.Retrofit
import com.example.testtaskforbootcamp.data.network.WordResponse
import com.example.testtaskforbootcamp.domain.AddWordItemUseCase
import com.example.testtaskforbootcamp.domain.GetWordItemUseCase
import com.example.testtaskforbootcamp.domain.GetWordListUseCase
import com.example.testtaskforbootcamp.domain.WordItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class MainViewModel  (application: Application):AndroidViewModel(application) {
    private val wordRepository = Retrofit()
    private val repository= WordListRepositoryImpl1(application)
    private val  addWordItemUseCase=AddWordItemUseCase(repository)
    private val  getWordItemUseCase=GetWordItemUseCase(repository)
    private val  getWordListCase= GetWordListUseCase(repository)
    private val  mapper=WordListMapper()
    private var wordItemId: Int = WordItem.UNDEFINED_ID

    val wordLiveData=MutableLiveData<WordResponse.WordResponseItem>()
    val wordDBLiveData=MutableLiveData<WordItem>()


    var list=  mutableSetOf<String>()
    val l= getWordListCase.getWordList()

    private val _errorInputName=MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _closeScreen=MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen


        init {
        fetchWord("hello")

            Log.d("addWordItem", fetchWord("hello").toString())
    }

   fun fetchWord(word:String) {
       val parseWord=parseInputName(word)
       val wordValid=validateInput(parseWord)
if(wordValid) {

    viewModelScope.launch(Dispatchers.IO) {

        if (list.isEmpty() || !list.contains(word.lowercase(Locale.getDefault()))) {
            val wordResponse = wordRepository.getWord(word)

            try {

                wordLiveData.postValue(wordResponse)

                    addWordItemUseCase.addWordItem(mapper.mapWordResponseToWordItem1(wordResponse))

                list.add(mapper.mapWordResponseToWordItem1(wordResponse).word.lowercase(Locale.getDefault()))

                Log.d("addWordItem ", " list ${list}")
                Log.d("addWordItem ", "db ${getWordItemUseCase.getWordItem(word).phonetic}")
            } catch (exception: Exception) {
                if (exception is HttpException) {

                }
            }

       // }else if(list.contains(word.lowercase(Locale.getDefault()))) {
        }else if(list.contains(word.lowercase(Locale.getDefault()))) {
            val dbWord = getWordItemUseCase.getWordItem(word.lowercase(Locale.getDefault()))
            wordDBLiveData.postValue(dbWord)
            Log.d("addWordItem ","kuuuu ${dbWord.phonetic}")
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
package com.example.testtaskforbootcamp.presentation


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val wordRepository = Retrofit()
    private val repository = WordListRepositoryImpl1(application)
    private val addWordItemUseCase = AddWordItemUseCase(repository)
    private val getWordItemUseCase = GetWordItemUseCase(repository)
    private val getWordListCase = GetWordListUseCase(repository)
    private val mapper = WordListMapper()


    val wordLiveData = MutableLiveData<WordResponse.WordResponseItem>()
    val wordDBLiveData = MutableLiveData<WordItem>()
    val wordList = getWordListCase.getWordList()

    var list = mutableSetOf<String>()


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen


    init {
        fetchWord("hello")

    }

    fun fetchWord(word: String) {
        val parseWord = parseInputName(word)
        val wordValid = validateInput(parseWord)
        if (wordValid) {

            viewModelScope.launch(Dispatchers.IO) {

                if (list.isEmpty() || !list.contains(word.lowercase(Locale.getDefault()))) {
                    val wordResponse = wordRepository.getWord(word)
                    val wordItem = mapper.mapWordResponseToWordItem1(wordResponse)

                    try {
                        val list1 = mutableListOf<WordItem>()
                        list1.add(mapper.mapWordResponseToWordItem1(wordResponse))
                        wordLiveData.postValue(wordResponse)

                        addWordItemUseCase.addWordItem(wordItem)

                        list.add(
                            mapper.mapWordResponseToWordItem1(wordResponse).word.lowercase(
                                Locale.getDefault()
                            )
                        )

                    } catch (exception: Exception) {
                        if (exception is HttpException) {
                            exception.message()
                        }
                    }

                } else if (list.contains(word.lowercase(Locale.getDefault()))) {
                    val dbWord = getWordItemUseCase.getWordItem(word.lowercase(Locale.getDefault()))

                    wordDBLiveData.postValue(dbWord)

                }
            }
        }
    }

    private fun validateInput(inputName: String): Boolean {
        var result = true
        if (inputName.isBlank()) {
            _errorInputName.value = true
            result = false
        }

        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    private fun parseInputName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }
}
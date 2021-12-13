package com.example.testtaskforbootcamp.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtaskforbootcamp.App
import com.example.testtaskforbootcamp.R
import com.example.testtaskforbootcamp.databinding.MainFragmentBinding
import dagger.Binds
import javax.inject.Inject

class MainFragment  : Fragment()  {



    private val viewBinding: MainFragmentBinding by viewBinding()


  @Inject
   lateinit var viewModelFactory:ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
    }



    private lateinit var word:TextView
    private lateinit var phonetics:TextView
    private lateinit var meanings:TextView
    private lateinit var enterWord:EditText
    private lateinit var generateButton:Button
    private lateinit var wordEnter:String




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        initViewBinding()
       // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        addTextChangeListeners()
        observeViewModel()
        setButtonListener()
       observeWordResponse()
observDB()

    }

@SuppressLint("SetTextI18n")
private fun observDB(){

    viewModel.wordDBLiveData.observe(viewLifecycleOwner,{
        word.text= " from database ${it.word}"
        phonetics.text=it.phonetic
        meanings.text=it.meanings
        Log.d("addWordItem","${it.phonetic}")
    })
}

    @SuppressLint("SetTextI18n")
    private fun observeWordResponse() {

        viewModel.wordLiveData.observe(viewLifecycleOwner) {response ->apply {
            val wordMeanings=SpannableString(     "definition :  \n ${response.meanings.indices.map { response.meanings }} \n" +
                    "example : \n ${response.meanings[0].definitions[0].example} \n" +
                    "synonyms : \n ${response.meanings[0].definitions[0].synonyms}")
            wordMeanings.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_200)), 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val wordText= SpannableString("word :  \n ${response.word}")
            wordText.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_200)), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val wordPhonetic= SpannableString("phonetics :  \n ${response.phonetics[0].text}")
            wordPhonetic.setSpan( ForegroundColorSpan(resources.getColor(R.color.purple_200)), 0, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            word.text= wordText
            phonetics.text=wordPhonetic
            meanings.text=wordMeanings







        }
        }
    }
    private fun setButtonListener() {
        generateButton.setOnClickListener {
         wordEnter=enterWord.text.toString()
            viewModel.fetchWord(wordEnter)

        }
    }
    private fun initViewBinding(){
         generateButton =viewBinding.generateButton
         word=viewBinding.word
         phonetics=viewBinding.phonetics
         meanings=viewBinding.meanings
         enterWord=viewBinding.etWord

    }

    private fun observeViewModel() {

        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            enterWord.error=message

        }
        viewModel.closeScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun addTextChangeListeners() {
        enterWord.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}
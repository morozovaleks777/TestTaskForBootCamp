package com.example.testtaskforbootcamp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtaskforbootcamp.R
import com.example.testtaskforbootcamp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewBinding: MainFragmentBinding by viewBinding()
    private lateinit var viewModel: MainViewModel

    private lateinit var word:TextView
    private lateinit var phonetics:TextView
    private lateinit var meanings:TextView
//    private lateinit var synonyms:TextView
//    private lateinit var partOfSpeech:TextView
//    private lateinit var example:TextView
//    private lateinit var definitions:TextView
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initViewBinding()
       observeWordResponse()
        setButtonListener()
    }



    @SuppressLint("SetTextI18n")
    private fun observeWordResponse() {
        viewModel.wordLiveData.observe(viewLifecycleOwner) {response ->apply {
            word.text=response.word
            phonetics.text= response.phonetics[0].text
            meanings.text="definition : ${response.meanings[0].definitions[0].definition} \n" +
                          "example : ${response.meanings[0].definitions[0].example} \n" +
                          "synonyms : ${response.meanings[0].definitions[0].synonyms}"



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
        // synonyms=viewBinding.synonyms
       //  partOfSpeech=viewBinding.partOfSpeech
       //  example=viewBinding.example
       //  definitions=viewBinding.definitions
        enterWord=viewBinding.etWord

    }


    companion object {
        fun newInstance() = MainFragment()
    }

}
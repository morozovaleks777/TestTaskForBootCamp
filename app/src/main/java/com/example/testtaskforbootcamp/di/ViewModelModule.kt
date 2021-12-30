package com.example.testtaskforbootcamp.di

import androidx.lifecycle.ViewModel
import com.example.testtaskforbootcamp.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun mainViewModel(viewModel: MainViewModel): ViewModel

}
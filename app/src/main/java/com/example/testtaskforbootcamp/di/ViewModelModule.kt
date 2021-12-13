package com.example.testtaskforbootcamp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtaskforbootcamp.presentation.MainViewModel
import com.example.testtaskforbootcamp.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("MainViewModel")
    @Binds
   fun mainViewModel(impl: MainViewModel):ViewModel


}




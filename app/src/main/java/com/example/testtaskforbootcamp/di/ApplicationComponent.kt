package com.example.testtaskforbootcamp.di

import com.example.testtaskforbootcamp.presentation.MainFragment
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(mainFragment: MainFragment)

}
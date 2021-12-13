package com.example.testtaskforbootcamp.di


import com.example.testtaskforbootcamp.data.database.WordListRepositoryImpl1
import com.example.testtaskforbootcamp.domain.WordListRepository
import dagger.Binds
import dagger.Module


@Module
 interface DomainModule {

    @Binds
    fun bindRepository(impl: WordListRepositoryImpl1): WordListRepository
}
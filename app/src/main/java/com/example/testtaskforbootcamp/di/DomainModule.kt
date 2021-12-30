package com.example.testtaskforbootcamp.di

import com.example.testtaskforbootcamp.data.database.WordListRepositoryImpl1
import com.example.testtaskforbootcamp.domain.WordListRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun bindRepository(impl: WordListRepositoryImpl1): WordListRepository {
        return impl
    }
}
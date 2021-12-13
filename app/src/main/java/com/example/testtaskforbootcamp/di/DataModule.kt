package com.example.testtaskforbootcamp.di


import androidx.room.RoomDatabase
import com.example.testtaskforbootcamp.data.database.AppDatabase
import com.example.testtaskforbootcamp.data.database.WordListRepositoryImpl1
import com.example.testtaskforbootcamp.data.database.WordResponseListDao
import com.example.testtaskforbootcamp.data.network.Retrofit
import com.example.testtaskforbootcamp.data.network.WordApi
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
 interface DataModule {
//    @ApplicationScope
//    @Binds
//  fun bindExampleLocalDataSource(impl: ):WordResponseListDao
   // @ApplicationScope
//    @Binds
//    fun bindExampleRemoteDataSource(impl: Retrofit): WordApi
@ApplicationScope
@Binds
fun bindAppDataBase(impl:AppDatabase):RoomDatabase
}
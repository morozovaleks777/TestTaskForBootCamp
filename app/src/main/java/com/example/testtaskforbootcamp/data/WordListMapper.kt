package com.example.testtaskforbootcamp.data



import com.example.testtaskforbootcamp.data.database.DBItem
import com.example.testtaskforbootcamp.data.network.WordResponse
import com.example.testtaskforbootcamp.domain.WordItem

class WordListMapper {

    fun mapWordResponseToWordItem1(wordItemDBModel: WordResponse.WordResponseItem): WordItem {
        return WordItem(
            meanings =   wordItemDBModel.meanings.indices.map { wordItemDBModel.meanings }.toString(),
            phonetic =wordItemDBModel.phonetics[0].text,
            word = wordItemDBModel.word
        )
    }

    fun mapWordItem1ToDbItem(wordItem:WordItem): DBItem {
        return DBItem(
            meanings = wordItem.meanings,
            phonetic =wordItem.phonetic,
            word = wordItem.word
        )
    }
    fun mapDBItemTOWordItem(dbItem: DBItem):WordItem{
        return WordItem(
            meanings = dbItem.meanings,
            phonetic =dbItem.phonetic,
            word = dbItem.word
        )
    }
    fun mapListDBModelToListEntity(list:List<DBItem>)=
        list.map {
           mapDBItemTOWordItem(it)
        }
    }

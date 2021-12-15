package com.example.testtaskforbootcamp.data


import com.example.testtaskforbootcamp.data.database.DBItem
import com.example.testtaskforbootcamp.data.network.WordResponse
import com.example.testtaskforbootcamp.domain.WordItem
import java.util.*

class WordListMapper {

    fun mapWordResponseToWordItem1(wordResponse: WordResponse.WordResponseItem): WordItem {
        return WordItem(
            itemId = wordResponse.itemId,
            meanings = wordResponse.meanings.indices.map { wordResponse.meanings }.toString(),
            phonetic = wordResponse.phonetics[0].text,
            word = wordResponse.word.lowercase(Locale.getDefault())
        )

    }

    fun mapWordItem1ToDbItem(wordItem: WordItem): DBItem {
        return DBItem(
            itemId = wordItem.itemId,
            meanings = wordItem.meanings,
            phonetic = wordItem.phonetic,
            word = wordItem.word
        )
    }

    fun mapDBItemTOWordItem(dbItem: DBItem): WordItem {
        return WordItem(
            itemId = dbItem.itemId,
            meanings = dbItem.meanings,
            phonetic = dbItem.phonetic,
            word = dbItem.word
        )
    }

    fun mapListDBModelToListEntity(list: List<DBItem>) =
        list.map {
            mapDBItemTOWordItem(it)
        }
}

package com.lambdaschool.sharedpreferencesm04.util

import androidx.lifecycle.LiveData
import com.lambdaschool.sharedpreferencesm04.model.BookItem

interface BookItemRepoInterface {
    fun createEntry(entry: BookItem)
    fun readAllEntries(): LiveData<List<BookItem>>
    fun updateEntry(entry: BookItem)
    fun deleteEntry(entry: BookItem)
}
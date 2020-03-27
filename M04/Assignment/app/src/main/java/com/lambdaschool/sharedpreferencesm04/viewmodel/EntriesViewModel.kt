package com.lambdaschool.sharedpreferencesm04.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.sharedpreferencesm04.model.BookItem
import com.lambdaschool.sharedpreferencesm04.util.repo

// TODO 24: Create a ViewModel for entries
class EntriesViewModel : ViewModel() {

    // TODO 25: Create a LiveData object for the entries
    val entries: LiveData<List<BookItem>> by lazy {
        readAllEntries()
    }

    // TODO 26: Recreate the repo calls to as functions here.
    fun readAllEntries() : LiveData<List<BookItem>> {
        return repo.readAllEntries()
    }

    fun createEntry(entry: BookItem) {
        repo.createEntry(entry)
    }

    fun updateEntry(entry: BookItem) {
        repo.updateEntry(entry)
    }



}
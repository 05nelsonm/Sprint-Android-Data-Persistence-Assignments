package com.lambdaschool.sharedprefsm04.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lambdaschool.sharedprefsm04.model.JournalEntry
import com.lambdaschool.sharedprefsm04.repo

// TODO 24: Create a ViewModel for entries
class EntriesViewModel : ViewModel() {

    // TODO 25: Create a LiveData object for the entries
    val entries: LiveData<List<JournalEntry>> by lazy {
        readAllEntries()
    }

    // TODO 26: Recreate the repo calls to as functions here.
    fun readAllEntries() : LiveData<List<JournalEntry>> {
        return repo.readAllEntries()
    }

    fun createEntry(entry: JournalEntry) {
        repo.createEntry(entry)
    }

    fun updateEntry(entry: JournalEntry) {
        repo.updateEntry(entry)
    }



}

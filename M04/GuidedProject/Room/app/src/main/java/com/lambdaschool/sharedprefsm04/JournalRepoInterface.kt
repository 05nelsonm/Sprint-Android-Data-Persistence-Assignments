package com.lambdaschool.sharedprefsm04

import androidx.lifecycle.LiveData
import com.lambdaschool.sharedprefsm04.model.JournalEntry

// TODO 4: We want the database repo to implement this interface

interface JournalRepoInterface {
    fun createEntry(entry: JournalEntry)
    fun readAllEntries(): LiveData<List<JournalEntry>>
    fun updateEntry(entry: JournalEntry)
    fun deleteEntry(entry: JournalEntry)
}
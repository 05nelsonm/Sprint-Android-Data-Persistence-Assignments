package com.lambdaschool.sharedprefsm02

import com.lambdaschool.sharedprefsm02.model.JournalEntry

// TODO 1: Extract behavior from Prefs to an interface

interface JournalRepoInterface {
    fun createEntry(entry: JournalEntry)
    fun readAllEntries(): MutableList<JournalEntry>
    fun updateEntry(entry: JournalEntry)
    fun deleteEntry(entry: JournalEntry)
}
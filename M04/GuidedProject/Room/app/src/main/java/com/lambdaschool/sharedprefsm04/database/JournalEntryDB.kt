package com.lambdaschool.sharedprefsm04.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.sharedprefsm04.model.JournalEntry

// TODO 14: Define the Room database (abstract class with an abstract method returning the DAO
@Database(entities = [JournalEntry::class], version = 2, exportSchema = false)
abstract class JournalEntryDB : RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDAO
}

package com.lambdaschool.sharedpreferencesm04.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lambdaschool.sharedpreferencesm04.model.BookItem
import com.lambdaschool.sharedpreferencesm04.util.BookItemRepoInterface

class BookDBRepo(val context: Context) : BookItemRepoInterface {

    override fun createEntry(entry: BookItem) {
        database.bookEntryDao().createEntry(entry)
    }

    override fun readAllEntries(): LiveData<List<BookItem>> {
        return database.bookEntryDao().readAllEntries()
    }

    override fun updateEntry(entry: BookItem) {
        database.bookEntryDao().updateEntry(entry)
    }

    override fun deleteEntry(entry: BookItem) {
        database.bookEntryDao().deleteEntry(entry)
    }

    private val database by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            BookEntryDB::class.java, "entry_database"
        ).fallbackToDestructiveMigration().build()
    }
}
package com.lambdaschool.sharedpreferencesm04.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.sharedpreferencesm04.model.BookItem

@Database(entities = [BookItem::class], version = 1, exportSchema = false)
abstract class BookEntryDB : RoomDatabase() {
    abstract fun bookEntryDao(): BookEntryDAO
}
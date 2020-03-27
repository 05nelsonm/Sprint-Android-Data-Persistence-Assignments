package com.lambdaschool.sharedpreferencesm04.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.sharedpreferencesm04.model.BookItem

@Dao
interface BookEntryDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createEntry(entry: BookItem)

    @Query("SELECT * FROM bookitem")
    fun readAllEntries(): LiveData<List<BookItem>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEntry(entry: BookItem)

    @Delete
    fun deleteEntry(entry: BookItem)
}
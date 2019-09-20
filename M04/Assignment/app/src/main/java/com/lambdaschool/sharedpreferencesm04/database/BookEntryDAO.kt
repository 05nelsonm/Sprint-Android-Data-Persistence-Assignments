package com.lambdaschool.sharedpreferencesm04.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.sharedpreferencesm04.model.BookItem

@Dao
interface BookEntryDAO {

    // TODO 10: Insert with onConflict = REPLACE
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun createEntry(entry: BookItem)

    // TODO 11: Query for all entities
    @Query("SELECT * FROM bookitem")
    fun readAllEntries(): LiveData<List<BookItem>> // TODO 27: Return LiveData for VM

    // TODO 12: Update with onConflict = REPLACE
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateEntry(entry: BookItem)

    // TODO 13: DELETE
    @Delete
    fun deleteEntry(entry: BookItem)
}
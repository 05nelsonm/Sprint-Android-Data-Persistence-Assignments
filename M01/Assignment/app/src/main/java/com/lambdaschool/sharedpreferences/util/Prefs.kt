package com.lambdaschool.sharedpreferences.util

import android.content.Context
import android.content.SharedPreferences
import com.lambdaschool.sharedpreferences.model.BookItem

class Prefs(context: Context) {

    companion object {
        private const val BOOK_PREFERENCES = "BookPreferences"
        private const val ID_LIST_KEY = "id_list"
        private const val BOOK_ITEM_KEY_PREFIX = "entry_"
        private const val NEXT_ID_KEY = "next_id"
    }

    val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(BOOK_PREFERENCES, Context.MODE_PRIVATE)

    // create a new entry
    fun createEntry(entry: BookItem) {
        // read list of entry ids
        val ids = getListOfIds()

        if (entry.id != BookItem.INVALID_ID && !ids.contains(entry.id.toString())) {
            // new entry
            val editor = sharedPrefs.edit()

            var nextId = sharedPrefs.getInt(NEXT_ID_KEY, 0)
            entry.id = nextId
            // store updated next id
            editor.putInt(NEXT_ID_KEY, ++nextId)

            // add id to list of ids
            ids.add(Integer.toString(entry.id))
            // store updated id list
            val newIdList = StringBuilder()
            for (id in ids) {
                newIdList.append(id).append(",")
            }

            editor.putString(ID_LIST_KEY, newIdList.toString())

            // store new entry
            editor.putString(BOOK_ITEM_KEY_PREFIX + entry.id, entry.toCsvString())
            editor.apply()
        } else {
            updateEntry(entry)
        }
    }

    private fun getListOfIds(): java.util.ArrayList<String> {
        val idList = sharedPrefs.getString(ID_LIST_KEY, "")
        val oldList = idList!!.split(",")

        val ids = ArrayList<String>(oldList.size)
        if (idList.isNotBlank()) {
            ids.addAll(oldList)
        }
        return ids
    }

    // read an existing entry
    private fun readEntry(id: Int): BookItem? {
        val entryCsv = sharedPrefs.getString(BOOK_ITEM_KEY_PREFIX + id, "invalid")!!
        return if (entryCsv != "invalid") {
            BookItem(entryCsv)
        } else {
            null
        }
    }

    // read all entries
    fun readAllEntries(): MutableList<BookItem> {
        // read list of entry ids
        val listOfIds = getListOfIds()

        // step through that list and read each entry
        val entryList = java.util.ArrayList<BookItem>()
        for (id in listOfIds) {
            if (id.isNotBlank()) {
                readEntry(id.toInt())?.let {
                    entryList.add(it)
                }
            }
        }
        return entryList
    }

    // edit an existing entry
    fun updateEntry(entry: BookItem) {
        val editor = sharedPrefs.edit()
        editor.putString(BOOK_ITEM_KEY_PREFIX + entry.id, entry.toCsvString())
        editor.apply()
    }
}
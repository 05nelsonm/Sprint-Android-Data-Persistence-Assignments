package com.lambdaschool.sharedpreferencesm04.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sharedpreferencesm04.R
import com.lambdaschool.sharedpreferencesm04.adapter.BookItemRecyclerViewAdapter
import com.lambdaschool.sharedpreferencesm04.model.BookItem
import com.lambdaschool.sharedpreferencesm04.util.Prefs

import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.content_main.*

class BookListActivity : AppCompatActivity() {

    companion object {
        const val NEW_ITEM_KEY = "98FH1PWO9UEHOJHQ90SA4GF"
        const val NEW_ITEM_CODE = 4506
        const val EDIT_ITEM_KEY = "908137GHFOIUHQWE09DF13"
        const val EDIT_ITEM_CODE = 8377
    }

    var adapter: BookItemRecyclerViewAdapter? = null
    var bookItemList = mutableListOf<BookItem>()
    var prefs: Prefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        setSupportActionBar(toolbar)

        prefs = Prefs(this)

        prefs?.let {
            bookItemList = it.readAllEntries()
        }

        book_item_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = BookItemRecyclerViewAdapter(bookItemList)
        book_item_list.layoutManager = manager
        book_item_list.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra(NEW_ITEM_KEY, bookItemList.size)
            startActivityForResult(intent, NEW_ITEM_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            val bookItem = BookItem(data?.getStringExtra(BookDetailActivity.DETAIL_ITEM_KEY).toString())

            if (requestCode == NEW_ITEM_CODE) {

                if (bookItem.id > -1) {
                    emptyTitleCheck(bookItem)
                    bookItemList.add(bookItem)
                    adapter?.notifyItemInserted(bookItemList.size - 1)

                    prefs?.let {
                        it.createEntry(bookItem)
                    }
                }

            } else if (requestCode == EDIT_ITEM_CODE) {

                if (bookItem.id > -1) {
                    emptyTitleCheck(bookItem)
                    bookItemList[bookItem.id] = bookItem
                    adapter?.notifyItemChanged(bookItem.id)

                    prefs?.let {
                        it.updateEntry(bookItem)
                    }
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun emptyTitleCheck(item: BookItem): BookItem {
        if (item.name == "") {
            item.name = "Please add a title"
        }
        return item
    }
}

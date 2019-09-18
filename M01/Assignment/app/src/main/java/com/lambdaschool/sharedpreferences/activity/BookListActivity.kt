package com.lambdaschool.sharedpreferences.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sharedpreferences.R
import com.lambdaschool.sharedpreferences.adapter.BookItemRecyclerViewAdapter
import com.lambdaschool.sharedpreferences.model.BookItem

import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.content_main.*

class BookListActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_ITEM_KEY = "98FH1PWO9UEHOJHQ90SA4GF"
        const val NEW_ITEM_CODE = 4506
        const val EDIT_ITEM_CODE = 8377
    }

    var adapter: BookItemRecyclerViewAdapter? = null
    val bookItemList = mutableListOf<BookItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        setSupportActionBar(toolbar)

        book_item_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = BookItemRecyclerViewAdapter(bookItemList)
        book_item_list.layoutManager = manager
        book_item_list.adapter = adapter

        fab.setOnClickListener { view ->
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra(DETAIL_ITEM_KEY, BookItem("", "", false, bookItemList.size))
            (this as Activity).startActivityForResult(intent, NEW_ITEM_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == NEW_ITEM_CODE && resultCode == Activity.RESULT_OK) {
            val newEntry = data?.getSerializableExtra(DETAIL_ITEM_KEY) as BookItem

            if (newEntry.id > -1) {

                emptyTitleCheck(newEntry)
                bookItemList.add(newEntry)
                adapter?.notifyItemInserted(bookItemList.size - 1)
            }
        } else if (requestCode == EDIT_ITEM_CODE && resultCode == Activity.RESULT_OK) {
            val modifiedEntry = data?.getSerializableExtra(DETAIL_ITEM_KEY) as BookItem

            if (modifiedEntry.id > -1) {

                emptyTitleCheck(modifiedEntry)
                bookItemList[modifiedEntry.id] = modifiedEntry
                adapter?.notifyItemChanged(modifiedEntry.id)
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

package com.lambdaschool.sharedpreferences.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sharedpreferences.R
import com.lambdaschool.sharedpreferences.adapter.BookItemRecyclerViewAdapter
import com.lambdaschool.sharedpreferences.model.BookItem

import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.content_main.*

class BookListActivity : AppCompatActivity() {

    val bookItemList = mutableListOf<BookItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        setSupportActionBar(toolbar)
        
        book_item_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = BookItemRecyclerViewAdapter(bookItemList)
        book_item_list.layoutManager = manager
        book_item_list.adapter = adapter

        fab.setOnClickListener { view ->
            Snackbar.make(view, "added an item", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}

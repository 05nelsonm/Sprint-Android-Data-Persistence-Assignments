package com.lambdaschool.sharedpreferences.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.sharedpreferences.R
import com.lambdaschool.sharedpreferences.model.BookItem
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    private var bookItem: BookItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            bookItem = bundle.getSerializable(BookListActivity.DETAIL_ITEM_KEY) as BookItem
            loadBookItemDetails(bookItem)
        }
    }

    override fun onBackPressed() {
        if (et_name.text.toString() == "" && et_reason.text.toString() == "") {
            super.onBackPressed()
        } else {
            val intent = Intent()
            intent.putExtra(
                BookListActivity.DETAIL_ITEM_KEY, BookItem(
                    et_name.text.toString(),
                    et_reason.text.toString(), cb_completed.isChecked, bookItem?.id ?: -1
                )
            )
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun loadBookItemDetails(item: BookItem?) {
        et_name.setText(item?.name ?: "")
        et_name.requestFocus()

        et_reason.setText(item?.reason ?: "")

        if (item!!.completed) {
            cb_completed.isChecked = true
        }
    }
}

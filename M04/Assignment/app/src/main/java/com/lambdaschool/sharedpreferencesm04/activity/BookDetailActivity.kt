package com.lambdaschool.sharedpreferencesm04.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.sharedpreferencesm04.R
import com.lambdaschool.sharedpreferencesm04.model.BookItem
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_ITEM_KEY = "08HSPOIDFH09I12HPFI"
    }

    private var bookItem: BookItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        if (intent.getSerializableExtra(BookListActivity.EDIT_ITEM_KEY) != null) {
            bookItem = intent.getSerializableExtra(BookListActivity.EDIT_ITEM_KEY) as BookItem
        } else {
            bookItem = intent.getSerializableExtra(BookListActivity.NEW_ITEM_KEY)
        }

        loadBookItemDetails(bookItem)
    }

    override fun onBackPressed() {

        if (et_name.text.toString() == "" && et_reason.text.toString() == "") {

            super.onBackPressed()

        } else {

            val intent = Intent()
            intent.putExtra(
                DETAIL_ITEM_KEY,
                BookItem(
                    et_name.text.toString(),
                    et_reason.text.toString(),
                    cb_completed.isChecked,
                    bookItem?.id ?: -1
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

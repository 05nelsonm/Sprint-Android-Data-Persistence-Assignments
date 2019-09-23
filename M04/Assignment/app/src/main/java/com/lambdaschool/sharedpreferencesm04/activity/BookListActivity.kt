package com.lambdaschool.sharedpreferencesm04.activity

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.lambdaschool.sharedpreferencesm04.R
import com.lambdaschool.sharedpreferencesm04.adapter.BookItemRecyclerViewAdapter
import com.lambdaschool.sharedpreferencesm04.model.BookItem
import com.lambdaschool.sharedpreferencesm04.util.Prefs
import com.lambdaschool.sharedpreferencesm04.viewmodel.EntriesViewModel

import kotlinx.android.synthetic.main.activity_book_list.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.ref.WeakReference

class BookListActivity : AppCompatActivity() {

    companion object {
        const val NEW_ITEM_KEY = "98FH1PWO9UEHOJHQ90SA4GF"
        const val NEW_ITEM_CODE = 4506
        const val EDIT_ITEM_KEY = "908137GHFOIUHQWE09DF13"
        const val EDIT_ITEM_CODE = 8377
    }

    lateinit var viewModel: EntriesViewModel
    var numEntries: Int = 0

    /*private var adapter: BookItemRecyclerViewAdapter? = null
    private var bookItemList = mutableListOf<BookItem>()
    private var prefs: Prefs? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        setSupportActionBar(toolbar)

        /*prefs = Prefs(this)

        prefs?.let {
            bookItemList = it.readAllEntries()
        }*/

        viewModel = ViewModelProviders.of(this).get(EntriesViewModel::class.java)

        ReadAllAsyncTask(this).execute()

        /*book_item_list.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = BookItemRecyclerViewAdapter(bookItemList)
        book_item_list.layoutManager = manager
        book_item_list.adapter = adapter*/

        fab.setOnClickListener {
            val intent = Intent(this@BookListActivity, BookDetailActivity::class.java)
            val bookItem = BookItem("", "", false, numEntries)
            intent.putExtra(NEW_ITEM_KEY, bookItem)
            startActivityForResult(intent, NEW_ITEM_CODE)
        }
    }

    private fun updateForEntries(entries: List<BookItem>) {
        listLayout.removeAllViews()
        entries.forEach { entry ->
            numEntries++
            listLayout.addView(createEntryView(entry))
        }
    }

    private fun createEntryView(entry: BookItem): TextView {
        val view = TextView(this@BookListActivity)

        view.text = entry.name

        view.setPadding(15, 15, 15, 15)
        view.textSize = 22f

        view.setOnClickListener {
            val viewDetailIntent = Intent(this@BookListActivity, BookDetailActivity::class.java)
            viewDetailIntent.putExtra(EDIT_ITEM_KEY, entry)
            startActivityForResult(
                viewDetailIntent,
                EDIT_ITEM_CODE
            )
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val bookItem = data?.getSerializableExtra(BookDetailActivity.DETAIL_ITEM_KEY) as BookItem

            if (requestCode == NEW_ITEM_CODE) {

                if (bookItem.id > -1) {
                    emptyTitleCheck(bookItem)
                    /*bookItemList.add(bookItem)
                    adapter?.notifyItemInserted(bookItemList.size - 1)

                    prefs?.let {
                        it.createEntry(bookItem)
                    }*/

                    CreateAsyncTask(viewModel).execute(bookItem)
                }

            } else if (requestCode == EDIT_ITEM_CODE) {

                if (bookItem.id > -1) {
                    emptyTitleCheck(bookItem)
                    /*bookItemList[bookItem.id] = bookItem
                    adapter?.notifyItemChanged(bookItem.id)

                    prefs?.let {
                        it.updateEntry(bookItem)
                    }*/

                    UpdateAsyncTask(viewModel).execute(bookItem)
                }

            }
        }
    }

    private fun emptyTitleCheck(item: BookItem): BookItem {
        if (item.name == "") {
            item.name = "Please add a title"
        }
        return item
    }

    class CreateAsyncTask(viewModel: EntriesViewModel) : AsyncTask<BookItem, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg entries: BookItem?) {
            if (entries.isNotEmpty()) {
                entries[0]?.let {
                    viewModel.get()?.createEntry(it)
                }
            }
        }
    }

    class UpdateAsyncTask(viewModel: EntriesViewModel) : AsyncTask<BookItem, Void, Unit>() {
        private val viewModel = WeakReference(viewModel)
        override fun doInBackground(vararg entries: BookItem?) {
            if (entries.isNotEmpty()) {
                entries[0]?.let {
                    viewModel.get()?.updateEntry(it)
                }
            }
        }
    }

    class ReadAllAsyncTask(activity: BookListActivity) : AsyncTask<Void, Void, LiveData<List<BookItem>>?>() {

        private val activity = WeakReference(activity)

        override fun doInBackground(vararg entries: Void?): LiveData<List<BookItem>>? {
            return activity.get()?.viewModel?.entries
        }

        override fun onPostExecute(result: LiveData<List<BookItem>>?) {
            activity.get()?.let { act ->
                result?.let { entries ->
                    // TODO 27: Observe LiveData here
                    entries.observe(act,
                        Observer<List<BookItem>> { t ->
                            t?.let {
                                act.updateForEntries(t)
                            }
                        }
                    )
                }
            }
        }
    }
}

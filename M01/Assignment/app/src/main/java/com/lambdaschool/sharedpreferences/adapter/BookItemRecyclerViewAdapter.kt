package com.lambdaschool.sharedpreferences.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.sharedpreferences.R
import com.lambdaschool.sharedpreferences.model.BookItem
import kotlinx.android.synthetic.main.book_item_layout.view.*

class BookItemRecyclerViewAdapter(private val data: MutableList<BookItem>) :
    RecyclerView.Adapter<BookItemRecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.tv_name
        val ivComplete: ImageView = view.iv_completed
        val bookItemContainer = view.ll_book_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.book_item_layout, parent, false)
        context = parent.context
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.tvName.text = item.name
        if (item.completed) {
            holder.ivComplete.visibility = View.VISIBLE
        } else {
            holder.ivComplete.visibility = View.INVISIBLE
        }

        // On Click Listener for the entry
        holder.bookItemContainer.setOnClickListener {
            // intent edit item activity for result
            //notifyItemChanged(position)
        }

        // Delete an item
        holder.bookItemContainer.setOnLongClickListener {

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Delete Item")
            builder.setMessage("Are you sure you want to delete ${item.name}?")
            builder.setPositiveButton("YES"){ dialog, which ->
                data.removeAt(position)
                notifyItemRemoved(position)
            }

            builder.setNegativeButton("No"){_, _ -> }

            val dialog: AlertDialog = builder.create()
            dialog.show()

            true
        }
    }
}
package com.lambdaschool.sharedpreferences.util

import com.lambdaschool.sharedpreferences.model.BookItem

fun bookIdRefactor(list: MutableList<BookItem>) {
    var i = 0
    list.forEach {
        it.id = i
        i++
    }
}
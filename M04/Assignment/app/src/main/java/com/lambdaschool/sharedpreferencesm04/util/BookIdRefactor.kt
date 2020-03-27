package com.lambdaschool.sharedpreferencesm04.util

import com.lambdaschool.sharedpreferencesm04.model.BookItem

fun bookIdRefactor(list: MutableList<BookItem>) {
    var i = 0
    list.forEach {
        it.id = i
        i++
    }
}
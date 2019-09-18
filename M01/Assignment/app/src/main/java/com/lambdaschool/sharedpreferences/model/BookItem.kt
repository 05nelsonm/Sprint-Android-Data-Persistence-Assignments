package com.lambdaschool.sharedpreferences.model

import java.io.Serializable

class BookItem(
    var name: String,
    var reason: String,
    var completed: Boolean,
    var id: Int
) : Serializable
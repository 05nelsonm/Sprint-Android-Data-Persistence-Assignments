package com.lambdaschool.sharedpreferencesm04.model

import java.io.Serializable

class BookItem: Serializable {

    companion object {
        const val INVALID_ID = -1
    }

    var name: String? = null
    var reason: String? = null
    var completed: Boolean = false
    var id: Int = 0

    constructor(name: String?, reason: String?, completed: Boolean, id: Int) {
        this.name = name
        this.reason = reason
        this.completed = completed
        this.id = id
    }

    constructor(csv: String) {
        val item = csv.split(",")
        this.name = item[0]
        this.reason = item[1]
        this.completed = item[2].toBoolean()
        this.id = item[3].toInt()
    }

    fun toCsvString(): String {
        return "$name,$reason,$completed,$id"
    }
}
package com.lambdaschool.sharedpreferences.model

import java.io.Serializable

class BookItem /*(
    var name: String,
    var reason: String,
    var completed: Boolean,
    var id: Int

)*/: Serializable {

    companion object {
        const val TAG = "BookItem"
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

    fun createCsvString(): String {
        return "$name,$reason,$completed,$id"
    }
}
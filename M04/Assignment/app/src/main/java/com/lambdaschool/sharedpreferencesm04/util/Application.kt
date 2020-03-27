package com.lambdaschool.sharedpreferencesm04.util

import android.app.Application
import com.lambdaschool.sharedpreferencesm04.database.BookDBRepo

val repo: BookItemRepoInterface by lazy {
    App.repo!!
}

class App : Application() {

    companion object {
        var repo: BookItemRepoInterface? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = BookDBRepo(applicationContext)
    }
}
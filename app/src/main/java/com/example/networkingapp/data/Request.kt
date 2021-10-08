package com.example.networkingapp.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class Request{

    companion object{
        private const val URL =  "https://api.github.com/search/repositories"
        private const val SEARCH = "q=super+mario+language:kotlin&sort=stars&order=desc"
        private const val COMPLETE_URL = "$URL?$SEARCH"
    }

    fun run():RepoResult {
        val repoListJsonStr = URL(COMPLETE_URL).readText()

        return Gson().fromJson(repoListJsonStr, RepoResult::class.java)
    }
}
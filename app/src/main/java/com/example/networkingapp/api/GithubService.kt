package com.example.networkingapp.api

import com.example.networkingapp.data.RepoResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GithubService {

    @GET("/repositories")
    suspend fun retrieveRepositories(): RepoResult

    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=50")
    suspend fun searchRepositories():  RepoResult

}
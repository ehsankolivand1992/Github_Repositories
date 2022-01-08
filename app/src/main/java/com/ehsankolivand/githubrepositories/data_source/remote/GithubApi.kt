package com.ehsankolivand.githubrepositories.data_source.remote

import androidx.lifecycle.LiveData
import com.ehsankolivand.githubrepositories.data_source.models.Contributor
import com.ehsankolivand.githubrepositories.data_source.models.Repo
import com.ehsankolivand.githubrepositories.data_source.models.RepoSearchResponse
import com.ehsankolivand.githubrepositories.data_source.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {


    @GET("search/repositories?q=tetris")
    suspend fun getRepos(): RepoSearchResponse

    /*@GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String, @Query("page") page: Int, ): RepoSearchResponse

    */
}

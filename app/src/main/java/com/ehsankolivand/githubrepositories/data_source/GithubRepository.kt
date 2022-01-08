package com.ehsankolivand.githubrepositories.data_source

import com.ehsankolivand.githubrepositories.data_source.remote.GithubApi
import javax.inject.Inject


class GithubRepository @Inject constructor(var githubApi: GithubApi){


    suspend fun fatchData()= githubApi.getRepos()

}
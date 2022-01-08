package com.ehsankolivand.githubrepositories.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ehsankolivand.githubrepositories.data_source.models.Contributor
import com.ehsankolivand.githubrepositories.data_source.models.Repo
import com.ehsankolivand.githubrepositories.data_source.models.RepoSearchResult
import com.ehsankolivand.githubrepositories.data_source.models.User


@Database(
    entities = [
        User::class,
        Repo::class,
        Contributor::class,
        RepoSearchResult::class
    ],
    version = 1
)
abstract class GithubCatchDatabase:  RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun repoDao(): RepoDao
}
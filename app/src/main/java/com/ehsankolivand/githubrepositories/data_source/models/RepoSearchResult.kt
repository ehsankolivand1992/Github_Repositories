package com.ehsankolivand.githubrepositories.data_source.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.ehsankolivand.githubrepositories.data_source.local.GithubTypeConverters

@Entity(primaryKeys = ["query"])
@TypeConverters(GithubTypeConverters::class)
class RepoSearchResult(
    val query: String,
    val repoIds: List<Int>,
    val totalCount: Int,
    val next: Int?
) {
}
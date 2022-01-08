package com.ehsankolivand.githubrepositories.data_source.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.ehsankolivand.githubrepositories.data_source.local.GithubTypeConverters

@Entity(primaryKeys = ["query"])
@TypeConverters(GithubTypeConverters::class)
class RepoSearchResult(//sirve para guardar los resultados en el sqlite
    val query: String,//ej:https://api.github.com/search/repositories?q=tetris
    val repoIds: List<Int>,//ej: List<Int>{1,2,3,4}...los ids de los repositorios encontrados
    val totalCount: Int,// el total de repositorios
    val next: Int?//el numero de pagina siguiente
) {
}
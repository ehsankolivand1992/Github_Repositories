package com.ehsankolivand.githubrepositories.di

import android.app.Application
import androidx.room.Room
import com.ehsankolivand.githubrepositories.data_source.local.GithubCatchDatabase
import com.ehsankolivand.githubrepositories.data_source.local.RepoDao
import com.ehsankolivand.githubrepositories.data_source.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GithubDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): GithubCatchDatabase =
        Room.databaseBuilder(application,GithubCatchDatabase::class.java,"GithubCatchDatabase")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    @Singleton
    fun provideUserDao(database: GithubCatchDatabase):UserDao{
        return database.userDao()
    }


    @Provides
    @Singleton
    fun provideRepoDao(database: GithubCatchDatabase):RepoDao{
        return database.repoDao()
    }
}
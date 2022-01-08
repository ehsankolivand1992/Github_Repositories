package com.ehsankolivand.githubrepositories.data_source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ehsankolivand.githubrepositories.data_source.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE login = :login")
    fun findByLogin(login: String): LiveData<User>
}
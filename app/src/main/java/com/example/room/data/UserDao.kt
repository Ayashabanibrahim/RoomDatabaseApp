package com.example.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.room.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addUser(user: User)
    @Query("SELECT * FROM user_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_data ")
    suspend fun deleteAllUsers()

}
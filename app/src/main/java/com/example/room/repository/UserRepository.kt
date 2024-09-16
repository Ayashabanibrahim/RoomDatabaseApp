package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.data.UserDao
import com.example.room.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData:LiveData<List<User>> = userDao.readAllData()
       fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}
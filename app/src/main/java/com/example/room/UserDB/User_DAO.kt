package com.example.room.UserDB

import androidx.room.*

@Dao
interface User_DAO {

    @Insert
    fun saveUser(user : User_Entity)

    @Query("select * from User_Entity")
    fun readUser() : List<User_Entity>

    @Query("DELETE from User_Entity where USER_NAME like :username")
    fun deleteThisUser( username : String )


}
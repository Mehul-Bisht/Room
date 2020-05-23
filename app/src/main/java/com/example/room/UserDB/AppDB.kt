package com.example.room.UserDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [(User_Entity::class)],version = 1)
    abstract class AppDB : RoomDatabase(){

    abstract fun userdao() : User_DAO
}
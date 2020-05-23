package com.example.room.UserDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User_Entity {
    @PrimaryKey
    var id : Int = 0

    @ColumnInfo(name = "USER_NAME")
    var username : String = ""

    @ColumnInfo(name = "USER_ROLL")
    var userroll: String = ""
}
package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.room.UserDB.AppDB
import com.example.room.UserDB.User_Entity
import kotlinx.android.synthetic.main.activity_intro.*
import java.sql.Timestamp
import java.util.*

class IntroActivity : AppCompatActivity() {

    var userid : Int ? = null

    var username : String ? = null

    var userroll : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java, "UserDB"
        ).build()

        val userName_tobesaved = enter_user_name.text
        val userRoll_tobesaved = enter_user_roll.text
        val usernameEdit = enter_username_to_be_deleted.text

        save.setOnClickListener(){
          if(userName_tobesaved == null)return@setOnClickListener
          if(userRoll_tobesaved == null)return@setOnClickListener


            Thread {
                val user = User_Entity()
                val timer = System.currentTimeMillis()
                user.id = timer.toInt()
                       user.username = userName_tobesaved.toString()
                       user.userroll = userRoll_tobesaved.toString()

                       db.userdao().saveUser(user)

            }.start()

        }

        view_all.setOnClickListener(){

            Thread{
                db.userdao().readUser().forEach() {
                    userid = it.id
                    Log.i("Mehul", "ID : ${it.id}")

                    username = it.username
                    Log.i("Mehul", "Name : ${it.username}")

                    userroll = it.userroll
                    Log.i("Mehul", "Roll : ${it.userroll}")
                }
            }.start()
        }

       delete_this_user.setOnClickListener(){
           if(usernameEdit == null)return@setOnClickListener
           Thread{
               db.userdao().deleteThisUser(usernameEdit.toString())
               Log.i("Mehul,Deleted username : ","$usernameEdit")
           }.start()

       }

    }
}

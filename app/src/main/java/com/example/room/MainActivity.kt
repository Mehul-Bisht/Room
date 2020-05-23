package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.room.UserDB.AppDB
import com.example.room.UserDB.User_Entity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.userdata.view.*

class MainActivity : AppCompatActivity() {

    var userid : Int ? = null

    var username : String? = null

    var userroll : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = Room.databaseBuilder(applicationContext,
            AppDB::class.java,"UserDB").build()

        val adapter = GroupAdapter<GroupieViewHolder>()
        recyclerview.adapter = adapter

        Thread {
            val user = User_Entity()
            var counter: Int

         /*   for (counter in 1..5) {
                user.id = counter
                user.username = "Mehul $counter"
                user.userroll = "Game Lead $counter"

                db.userdao().saveUser(user)
            }*/
                db.userdao().readUser().forEach() {
                    userid = it.id
                    Log.i("Mehul", "ID : ${it.id}")

                    username = it.username
                    Log.i("Mehul", "Name : ${it.username}")

                    userroll = it.userroll
                    Log.i("Mehul", "Roll : ${it.userroll}")

                    adapter.add(userdisplay(userid!!, username!!, userroll!!))
                   
                }

        }.start()

    }

    class userdisplay(val passID : Int,val passName : String,val passRoll : String) : Item<GroupieViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.userdata
        }

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.user_id_value.text = passID.toString()
            viewHolder.itemView.username_value.text = passName
            viewHolder.itemView.user_roll_value.text = passRoll
        }
    }
}

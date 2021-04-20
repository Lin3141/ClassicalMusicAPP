package com.example.activitytest.search.comment

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CommentDatabaseHelper(val context: Context, name:String, version:Int):SQLiteOpenHelper(context,name, null,version){
    //statement for creating a table
    private val createComment = "create table Comments (" +
            " id integer primary key autoincrement," +
            "username text," +
            "songName text," +
            "comment text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createComment)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}
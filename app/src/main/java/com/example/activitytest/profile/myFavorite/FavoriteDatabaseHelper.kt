package com.example.activitytest.profile.myFavorite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class FavoriteDatabaseHelper(val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {
    private val createFavorite = "create table Favorite (" +
            "id integer primary key autoincrement," +
            "musicId text," +
            "musicName text)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createFavorite)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}
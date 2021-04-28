package com.example.activitytest.profile.myFavorite

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.example.activitytest.R
import com.example.activitytest.profile.BaseActivity
import com.example.activitytest.search.MusicPlayerActivity

class FavoriteActivity : BaseActivity() {
    private val favoriteList = ArrayList<Music>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_layout)
        initFavoriteList()
        val adapter = FavoriteAdapter(this, R.layout.item_favorite,favoriteList)
        val listView:ListView=findViewById(R.id.favoriteList)
        listView.adapter=adapter
        listView.setOnItemClickListener{ _, _, position, _ ->
            val song = favoriteList[position]
            val intent = Intent(this, MusicPlayerActivity::class.java)
            intent.putExtra("songId",song.musicId)
            intent.putExtra("name", song.name)
            startActivity(intent)
        }
    }

    private fun initFavoriteList() {
        val dbHelper = FavoriteDatabaseHelper(this,"Favorite.db",1)
        val db = dbHelper.writableDatabase
        val cursor = db.query("Favorite", null, null, null, null, null, null)
        if(cursor.moveToFirst()){
            do{
                val musicName = cursor.getString(cursor.getColumnIndex("musicName"))
                val musicId = cursor.getInt(cursor.getColumnIndex("musicId"))
                favoriteList.add(Music(musicName, musicId))
            }while(cursor.moveToNext())
        }
        cursor.close()
    }
}
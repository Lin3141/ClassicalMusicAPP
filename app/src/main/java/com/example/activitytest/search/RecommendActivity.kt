package com.example.activitytest.search

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread


class RecommendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recommend_layout)
        val id = intent.getIntExtra("songId",0)
        getRecommend(id)
    }

    private fun getRecommend(id: Int) {
        thread{
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url("http://192.168.110.99:3000/simi/song?id=$id")
                        .build()
                val response = client.newCall(request).execute()
                var responseData = response.body?.string()
                if(responseData!=null){
                    runOnUiThread {
                        parseJSONWithGSON(responseData)
                    }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithGSON(responseData: String) {
        val gson = Gson()
        val song = gson.fromJson(responseData, RecommendSong::class.java)
        val songs= song.songs
        if(songs!=null) {
            val adapter = RecommendAdapter(this, R.layout.song_layout, songs)
            val listView: ListView = findViewById(R.id.recommendList)
            listView.adapter = adapter
            listView.setOnItemClickListener { _, _, position, _ ->
                val song = songs[position]
                val qText = song.id
                val intent = Intent(this, MusicPlayerActivity::class.java)
                intent.putExtra("songId",qText)
                intent.putExtra("name", song.name)
                startActivity(intent)
            }
        }
    }
}
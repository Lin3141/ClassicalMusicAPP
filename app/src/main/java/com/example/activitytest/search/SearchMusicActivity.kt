package com.example.activitytest.search

import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.example.activitytest.R
import com.example.activitytest.profile.BaseActivity
import com.google.gson.Gson
import okhttp3.*
import kotlin.concurrent.thread


class SearchMusicActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_music_layout)
        val search: Button = findViewById(R.id.search)
        search.setOnClickListener {
            searchMusic()
        }

    }

    private fun searchMusic(){
          thread{
              try {
                  val client = OkHttpClient()
                  val q:EditText = findViewById(R.id.input_message)
                  val qText = q.text
                  val request = Request.Builder()
                          .url("http://musicapi.leanapp.cn/search?keywords=$qText")
                          .build()
                  val response = client.newCall(request).execute()
                  val responseData = response.body?.string()
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
        val gson=Gson()
        val song = gson.fromJson(responseData, Song::class.java)
        val songs= song.result.songs
        if(songs!=null) {
            val adapter = MusicAdapter(this, R.layout.song_layout, songs)
            val listView: ListView = findViewById(R.id.searchResult)
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
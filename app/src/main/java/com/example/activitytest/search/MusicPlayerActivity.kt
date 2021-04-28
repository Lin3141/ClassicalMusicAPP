package com.example.activitytest.search

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.activitytest.R
import com.example.activitytest.profile.BaseActivity
import com.example.activitytest.profile.myFavorite.FavoriteDatabaseHelper
import com.example.activitytest.search.comment.CommentActivity


class MusicPlayerActivity : BaseActivity() {
    val mediaPlayer = MediaPlayer()
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player_layout)

        val songId = intent.getIntExtra("songId",0)
        val id = "https://music.163.com/song/media/outer/url?id=$songId.mp3"
        val name = intent.getStringExtra("name")
        val songName : TextView = findViewById(R.id.songName)
        songName.text = name
        val dbHelper = FavoriteDatabaseHelper(this, "Favorite.db", 1)
        val db = dbHelper.writableDatabase
        try {
            if (id != null) {
                mediaPlayer.setDataSource(id)
                mediaPlayer.prepare()
            }
            val play: Button = findViewById(R.id.play)
            val seekbar: SeekBar = findViewById(R.id.seekBar)
            seekbar.max = mediaPlayer.duration
            play.setOnClickListener(playlis)
            seekbar.setOnSeekBarChangeListener(sbLis)
        }catch(e:Exception){
            Toast.makeText(this,"Do not have music right yet.",Toast.LENGTH_SHORT).show()
        }
        //like
        val like: Button = findViewById(R.id.like)
        val cursor=db.query("Favorite",null,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do{
                val musicName = cursor.getString(cursor.getColumnIndex("musicName"))
                if(musicName == name){
                    like.text = "liked"
                }
            }while(cursor.moveToNext())
        }
        cursor.close()
        like.setOnClickListener {
            if (like.text == "like") {
                like.text = "liked"
                Toast.makeText(this, "You liked this track.", Toast.LENGTH_SHORT).show()
                val values = ContentValues().apply{
                    put("musicId", songId)
                    put("musicName", name)
                }
                db.insert("Favorite", null, values)
            } else {
                like.text = "like"
                Toast.makeText(this, "You disliked this track.", Toast.LENGTH_SHORT).show()
                val args = arrayOf(songId.toString())
                db.delete("Favorite", "musicID=?", args)
            }
        }
        //comment
        val comment: Button = findViewById(R.id.comments)
        comment.setOnClickListener{
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("songName", name)
            startActivity(intent)
        }
        //set up sleep time
        val sleepTime:Button = findViewById(R.id.sleepTime)
        sleepTime.setOnClickListener {
            val intent = Intent(this, SleepTimeActivity::class.java)
            startActivity(intent)
        }
        //recommend
        val recommend:Button = findViewById(R.id.recommend)
        recommend.setOnClickListener {
            val intent = Intent(this, RecommendActivity::class.java)
            intent.putExtra("songId", songId)
            startActivity(intent)
        }
    }

    private val playlis = View.OnClickListener {
        val play: Button = findViewById(R.id.play)
        if(!mediaPlayer.isPlaying){
            handler.post(start)
            play.text="pause"

        }else{
            mediaPlayer.pause()
            play.text="play"
        }
    }
    private var start = Runnable {
        mediaPlayer.start()
        handler.post(updatesb)
    }
    private var updatesb: Runnable = object : Runnable {
        override fun run() {
            val seekbar : SeekBar = findViewById(R.id.seekBar)
           if(mediaPlayer.isPlaying) {
               seekbar.progress = mediaPlayer.currentPosition
               handler.postDelayed(this, 1000)
           }
        }
    }

    private val sbLis: SeekBar.OnSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int,
                                       fromUser: Boolean) {
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            if (seekBar != null) {
                mediaPlayer.seekTo(seekBar.progress)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
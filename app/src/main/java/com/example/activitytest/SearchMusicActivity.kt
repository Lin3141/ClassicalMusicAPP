package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitytest.R
import com.example.activitytest.profile.BaseActivity

class SearchMusicActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_music_layout)
    }
}
package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.activitytest.profile.BaseActivity
import com.example.activitytest.search.SearchMusicActivity
import com.example.activitytest.ui.login.LoginActivity


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
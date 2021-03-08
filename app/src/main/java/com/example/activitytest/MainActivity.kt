package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.R
import com.example.activitytest.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener{
            val intent = Intent(this,SearchMusicActivity::class.java)
            startActivity(intent)
        }
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
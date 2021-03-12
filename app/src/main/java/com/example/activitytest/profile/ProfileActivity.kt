package com.example.activitytest.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import com.example.activitytest.MainActivity
import com.example.activitytest.R
import com.example.activitytest.SearchMusicActivity
import com.example.activitytest.settings.SettingsActivity
import com.example.activitytest.ui.login.LoginActivity

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)
        val favorite: Button = findViewById(R.id.favorite)
        favorite.setOnClickListener{
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        val comment: Button = findViewById(R.id.comment)
        comment.setOnClickListener{
            val intent = Intent(this, CommentActivity::class.java)
            startActivity(intent)
        }
        val search: Button = findViewById(R.id.search)
        search.setOnClickListener{
            val intent = Intent(this, SearchMusicActivity::class.java)
            startActivity(intent)
        }
        val log_out: Button = findViewById(R.id.logout)
        log_out.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            ActivityCollector.finishAll()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this,SettingsActivity::class.java)
        when(item.itemId){
            R.id.action_settings -> startActivity(intent)
        }
        return true
    }
}
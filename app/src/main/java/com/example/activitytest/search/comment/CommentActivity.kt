package com.example.activitytest.search.comment

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.activitytest.R
import java.util.*


class CommentActivity : Activity(), View.OnClickListener {
    private var comment: ImageView? = null
    private var hide_down: TextView? = null
    private var comment_content: EditText? = null
    private var comment_send: Button? = null
    private var rl_enroll: LinearLayout? = null
    private var rl_comment: RelativeLayout? = null
    private var comment_list: ListView? = null
    private var adapterComment: AdapterComment? = null
    private var songName: String? = null
    private var data: List<Comment>? = null
    private val dbHelper = CommentDatabaseHelper(this, "CommentDatabase.db", 1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comments_layout)
        initView()
    }

    private fun initView() {

        comment_list = findViewById<View>(R.id.comment_list) as ListView
        data = ArrayList()
        adapterComment = AdapterComment(applicationContext, data as ArrayList<Comment>)
        comment_list!!.adapter = adapterComment
        comment = findViewById<View>(R.id.comment) as ImageView
        hide_down = findViewById<View>(R.id.hide_down) as TextView
        comment_content = findViewById<View>(R.id.comment_content) as EditText
        comment_send = findViewById<View>(R.id.comment_send) as Button
        rl_enroll = findViewById<View>(R.id.rl_enroll) as LinearLayout
        rl_comment = findViewById<View>(R.id.rl_comment) as RelativeLayout
        songName = intent.getStringExtra("songName")
        val db = dbHelper.writableDatabase
        val cursor: Cursor? = db.query("Comments", null, null, null, null, null, null)
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    val username = cursor.getString(cursor.getColumnIndex("username"))
                    val comment = cursor.getString(cursor.getColumnIndex("comment"))
                    adapterComment?.addComment(Comment("$username: ", comment))
                }while(cursor.moveToNext())
            }
        }
        cursor?.close()
        setListener()
    }

    private fun setListener() {
        comment!!.setOnClickListener(this)
        hide_down!!.setOnClickListener(this)
        comment_send!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.comment -> {
                val imm = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
                rl_enroll!!.visibility = View.GONE
                rl_comment!!.visibility = View.VISIBLE
            }
            R.id.hide_down -> {
                rl_enroll!!.visibility = View.VISIBLE
                rl_comment!!.visibility = View.GONE
                val im = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                im.hideSoftInputFromWindow(comment_content!!.windowToken, 0)
            }
            R.id.comment_send -> sendComment()
            else -> {
            }
        }
    }

    private fun sendComment() {
        if (comment_content!!.text.toString() == "") {
            Toast.makeText(applicationContext, "Comments should not be empty！", Toast.LENGTH_SHORT).show()
        } else {
            val comment = Comment()
            val prefs = getSharedPreferences("account", Context.MODE_PRIVATE)
            val username = prefs.getString("username", "")
            comment.name = "$username："
            comment.content = comment_content!!.text.toString()
            adapterComment?.addComment(comment)
            //store comments into database
            val value = ContentValues().apply{
                put("username", username)
                put("songName", songName)
                put("comment", comment_content!!.text.toString())
            }
            val db = dbHelper.writableDatabase
            db.insert("Comments", null, value)
            comment_content!!.setText("")
            Toast.makeText(applicationContext, "Commented successfully！", Toast.LENGTH_SHORT).show()
        }
    }
}
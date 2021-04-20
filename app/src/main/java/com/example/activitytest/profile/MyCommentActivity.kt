package com.example.activitytest.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.activitytest.R
import com.example.activitytest.search.comment.AdapterComment
import com.example.activitytest.search.comment.Comment
import com.example.activitytest.search.comment.CommentDatabaseHelper
import java.util.*


class MyCommentActivity : BaseActivity() {
    private var adapterComment: AdapterComment? = null
    private var data: List<Comment>? = null
    private var commentList: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_comment_layout)
        commentList = findViewById<View>(R.id.commentList) as ListView
        val dbHelper = CommentDatabaseHelper(this, "CommentDatabase.db", 1)
        val db = dbHelper.writableDatabase
        val cursor: Cursor? = db.query("Comments", null, null, null, null, null, null)
        data = ArrayList()
        adapterComment = AdapterComment(applicationContext, data as ArrayList<Comment>)
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    val songName = cursor.getString(cursor.getColumnIndex("songName"))
                    val comment = cursor.getString(cursor.getColumnIndex("comment"))
                    adapterComment?.addComment(Comment("$songName: ", comment))
                }while(cursor.moveToNext())
            }
        }
        commentList!!.adapter = adapterComment
        cursor?.close()
        commentList!!.setOnItemClickListener { _, _, position, _ ->
            val comment = (data as ArrayList<Comment>)[position]
            val alertDialog: AlertDialog = AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Are you going to delete the comment?")
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    })
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                        val args = arrayOf(comment.content)
                        db.delete("Comments", "comment=?", args)
                        adapterComment!!.removeComment(comment)
                        Toast.makeText(this, "You have successfully deleted this comment", Toast.LENGTH_SHORT).show()
                    })
                    .create()
            alertDialog.show()
        }
    }
}
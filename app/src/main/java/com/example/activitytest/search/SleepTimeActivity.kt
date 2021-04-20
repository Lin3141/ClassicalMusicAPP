package com.example.activitytest.search

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.activitytest.R
import com.example.activitytest.profile.ActivityCollector
import com.example.activitytest.profile.BaseActivity
import java.util.*
import kotlin.concurrent.thread


class SleepTimeActivity : BaseActivity() {
    private val timer = Timer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sleep_time_layout)
        val sleepTime:EditText = findViewById(R.id.sleepTime)
        val submit:Button = findViewById(R.id.submitTime)
        submit.setOnClickListener {
            val time = sleepTime.text.toString()
            val parsedLong = time.toLongOrNull()
            if (parsedLong == null)  {
                Toast.makeText(this,"Not a valid input.",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Music will close in $parsedLong minute(s)",Toast.LENGTH_SHORT).show()
                sleepTime.setText("")
                timer.schedule(RemindTask(), parsedLong*60 * 1000)
            }
        }
    }

    internal inner class RemindTask : TimerTask() {
        override fun run() {
            timer.cancel()
            ActivityCollector.finishAll()
            android.os.Process.killProcess(android.os.Process.myPid())
        }
    }
}
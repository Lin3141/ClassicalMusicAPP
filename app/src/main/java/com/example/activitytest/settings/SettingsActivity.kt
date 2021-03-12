package com.example.activitytest.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.activitytest.R
import com.example.activitytest.profile.BaseActivity

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_layout)
        // change the password
        val change_pw : Button = findViewById(R.id.change_password)
        val pw : EditText = findViewById(R.id.passwords)
        change_pw.setOnClickListener{
            val editor = getSharedPreferences("account", Context.MODE_PRIVATE).edit()
            if(pw.getText().toString().length>5){
                editor.putString("password", pw.getText().toString())
                editor.apply()
                Toast.makeText(this, "Successfully changed the password", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Password must be >5 characters", Toast.LENGTH_SHORT).show()
            }
            pw.setText("")
        }
        // submit the feedback
        val submit_feedback : Button = findViewById(R.id.feedback)
        val feedback : EditText = findViewById(R.id.feedbacks)
        submit_feedback.setOnClickListener{
            val editor = getSharedPreferences("account", Context.MODE_PRIVATE).edit()
            if(feedback.getText().toString().length>0) {
                editor.putString("feedback", feedback.getText().toString())
                editor.apply()
                Toast.makeText(this, "Successfully submitted the feedback", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Feedback must be >1 characters", Toast.LENGTH_SHORT).show()
            }
            feedback.setText("")
        }
    }
}
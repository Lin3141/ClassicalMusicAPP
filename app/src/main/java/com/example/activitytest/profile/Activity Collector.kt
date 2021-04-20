package com.example.activitytest.profile

import android.app.Activity
import java.util.concurrent.CopyOnWriteArrayList

object ActivityCollector {
    private val activities = CopyOnWriteArrayList<Activity>()

    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    fun finishAll(){
        for(activity in activities){
            if(!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }
}
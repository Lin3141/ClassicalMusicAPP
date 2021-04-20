package com.example.activitytest.profile.myFavorite

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.activitytest.R

class FavoriteAdapter(activity: Activity, val resourceId: Int, data: List<Music>): ArrayAdapter<Music>(activity, resourceId, data) {
    inner class ViewHolder(val musicName: TextView)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if(convertView==null){
            view=LayoutInflater.from(context).inflate(resourceId, parent, false)
            val musicName: TextView = view.findViewById(R.id.favoriteName)
            viewHolder = ViewHolder(musicName)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val music = getItem(position)
        if(music!=null){
            viewHolder.musicName.text=music.name
        }
        return view
    }
}
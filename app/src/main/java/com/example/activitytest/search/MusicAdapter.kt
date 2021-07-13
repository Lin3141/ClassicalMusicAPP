package com.example.activitytest.search


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.activitytest.R
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MusicAdapter(activity: Activity, val resourceId:Int, val data: List<SongX>): ArrayAdapter<SongX>(activity,resourceId,data){

    inner class ViewHolder(val songName: TextView, val artist: TextView)
    val activity=activity
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if(convertView == null){
            view =LayoutInflater.from(context).inflate(resourceId, parent, false)
            val songName:TextView = view.findViewById(R.id.songName)
            val artist:TextView = view.findViewById(R.id.artist)
            viewHolder = ViewHolder(songName, artist)
            view.tag=viewHolder
        }else{
            view=convertView
            viewHolder=view.tag as ViewHolder
        }
        val song = getItem(position)
        thread{
            try{
                val client = OkHttpClient()
                val id = song?.id
                val request = Request.Builder().url("https://music-api.heheda.top/song/url?id=$id").build()
               val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if(responseData!=null){
                    val gson= Gson()
                    val playInfo = gson.fromJson(responseData, Playable::class.java)
                  val playable= playInfo.success
                    activity.runOnUiThread{
                        if(song!=null && playable) {
                            viewHolder.songName.text=song.name
                            viewHolder.artist.text= song.artists[0].name
                        }
                    }
                }
            }catch(e: Exception){
                e.printStackTrace()
            }
        }

        return view
    }
}
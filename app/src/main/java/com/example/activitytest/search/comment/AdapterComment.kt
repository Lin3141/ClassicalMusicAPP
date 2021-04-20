package com.example.activitytest.search.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.activitytest.R

class AdapterComment(var context: Context, var data: MutableList<Comment>) : BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(i: Int): Any {
        return data[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View? {
        var convertView:View? = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null)
            holder.comment_name = convertView.findViewById<View>(R.id.comment_name) as TextView
            holder.comment_content = convertView.findViewById<View>(R.id.comment_content) as TextView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.comment_name!!.text = data[i].name
        holder.comment_content!!.text = data[i].content
        return convertView
    }

    fun addComment(comment: Comment) {
        data.add(comment)
        notifyDataSetChanged()
    }

    fun removeComment(comment:Comment) {
        data.remove(comment)
        notifyDataSetChanged()
    }

    class ViewHolder {
        var comment_name: TextView? = null
        var comment_content: TextView? = null
    }

}
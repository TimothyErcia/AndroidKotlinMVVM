package com.example.androidkotlinmvvm.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.androidkotlinmvvm.R
import com.example.androidkotlinmvvm.model.Photos

class PhotosItemAdapter(_context: Context): RecyclerView.Adapter<PhotosItemAdapter.mViewHolder>() {

    var photosList: List<Photos> = ArrayList<Photos>()
    private val context: Context = _context

    fun setPhotoList(photoList: List<Photos>) {
        this.photosList = photoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_list_item, parent, false)
        return mViewHolder(view)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.titleTextView.text = photosList[position].title
        holder.bodyTextView.text = photosList[position].url
        holder.urlTextView.text = photosList[position].thumbnailUrl
        val url = GlideUrl(photosList[position].thumbnailUrl, LazyHeaders.Builder().addHeader("User-agent", "your-user-agent").build())
        Glide.with(context)
            .asBitmap()
            .load(url)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleText)
        val bodyTextView: TextView = itemView.findViewById(R.id.bodyText)
        val urlTextView: TextView = itemView.findViewById(R.id.urlText)
    }
}
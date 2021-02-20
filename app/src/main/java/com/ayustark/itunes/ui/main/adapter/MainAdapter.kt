package com.ayustark.itunes.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayustark.itunes.R
import com.ayustark.itunes.data.model.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val songs: ArrayList<Result>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(song: Result) {
            itemView.textViewUserName.text = song.artistName
            itemView.textViewUserEmail.text = song.collectionName
            Glide.with(itemView.imageViewAvatar.context)
                .load(song.artworkUrl100)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(songs[position])

    fun addData(list: List<Result>) {
        songs.addAll(list)
    }

}
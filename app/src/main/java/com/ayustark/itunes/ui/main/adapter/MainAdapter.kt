package com.ayustark.itunes.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayustark.itunes.R
import com.ayustark.itunes.data.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val songs: ArrayList<Result>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(song: Result) {
            itemView.txtTitle1.text = song.trackName
            itemView.txtArtist1.text = song.artistName
            itemView.txtColl1.text = song.collectionName
            Picasso.get().load(song.artworkUrl100).error(R.mipmap.ic_song)
                .into(itemView.imgSong1)
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
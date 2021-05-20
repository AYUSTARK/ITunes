package com.ayustark.itunes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayustark.itunes.R
import com.ayustark.itunes.data.repository.SearchEntity
import com.ayustark.itunes.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

class MainAdapter(private val songs: ArrayList<SearchEntity>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(song: SearchEntity) {
            binding.txtTitle1.text = song.trackName
            binding.txtArtist1.text = song.artistName
            binding.txtColl1.text = song.collectionName
            Picasso.get().load(song.artworkUrl100).error(R.mipmap.ic_song)
                .into(binding.imgSong1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(songs[position])

    fun addData(list: List<SearchEntity>) {
        songs.addAll(list)
    }

}
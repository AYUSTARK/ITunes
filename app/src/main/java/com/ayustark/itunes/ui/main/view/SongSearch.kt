package com.ayustark.itunes.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.ayustark.itunes.databinding.SearchSongBinding

class SongSearch : AppCompatActivity() {
    lateinit var binding: SearchSongBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingActionButton.setOnClickListener {
            val artist = binding.etArtist.text.toString()
            if (artist.length >= 4) {
                intent = Intent(this@SongSearch, MainActivity::class.java)
                intent.putExtra("artist", artist)
                startActivity(intent)
            }
        }

        binding.etArtist.doOnTextChanged { inputText, _, _, _ ->
            if (inputText?.length!! >= 4) {
                binding.floatingActionButton.visibility = View.VISIBLE
            } else {
                binding.floatingActionButton.visibility = View.INVISIBLE
            }
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                binding.floatingActionButton.performClick()
                true
            }
            else ->
                return super.onKeyUp(keyCode, event)

        }
    }
}
package com.ayustark.itunes.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.ayustark.itunes.R
import kotlinx.android.synthetic.main.search_song.*

class SongSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_song)
        floating_action_button.setOnClickListener {
            val artist = etArtist.text.toString()
            if (artist.length >= 4) {
                intent = Intent(this@SongSearch, MainActivity::class.java)
                intent.putExtra("artist", artist)
                startActivity(intent)
            }
        }
        etArtist.doOnTextChanged { inputText, _, _, _ ->
            if (inputText?.length!! >= 4) {
                floating_action_button.visibility = View.VISIBLE
            } else {
                floating_action_button.visibility = View.INVISIBLE
            }
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                floating_action_button.performClick()
                true
            }
            else ->
                return super.onKeyUp(keyCode, event)

        }
    }
}
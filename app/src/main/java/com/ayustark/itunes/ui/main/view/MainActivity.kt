package com.ayustark.itunes.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.data.api.SongsApi
import com.ayustark.itunes.data.repository.MainRepository
import com.ayustark.itunes.data.repository.SearchDao
import com.ayustark.itunes.data.repository.SearchDatabase
import com.ayustark.itunes.data.repository.SearchEntity
import com.ayustark.itunes.databinding.ActivityMainBinding
import com.ayustark.itunes.ui.base.ViewModelFactory
import com.ayustark.itunes.ui.main.adapter.MainAdapter
import com.ayustark.itunes.ui.main.viewmodel.MainViewModel
import com.ayustark.itunes.utils.Status
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: MainRepository
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var artist = " "
        if (intent != null) {
            artist = intent?.getStringExtra("artist").toString()
        }
        runOnUiThread {

        }
        val disp = "Showing results for $artist"
        binding.txtResult.text = disp
        setupUI()
        val dao = SearchDatabase.getInstance(application).searchDao
        setupViewModel(artist, dao)
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as GridLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<SearchEntity>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel(author: String, dao: SearchDao) {
        repository = MainRepository(ApiHelper(SongsApi), author.toLowerCase(Locale.ROOT), dao)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(repository)
        ).get(MainViewModel::class.java)
    }
}
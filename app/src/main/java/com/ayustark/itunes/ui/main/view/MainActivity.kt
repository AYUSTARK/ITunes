package com.ayustark.itunes.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.ayustark.itunes.R
import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.data.api.SongsApi
import com.ayustark.itunes.data.repository.MainRepository
import com.ayustark.itunes.data.repository.SearchDao
import com.ayustark.itunes.data.repository.SearchDatabase
import com.ayustark.itunes.data.repository.SearchEntity
import com.ayustark.itunes.ui.base.ViewModelFactory
import com.ayustark.itunes.ui.main.adapter.MainAdapter
import com.ayustark.itunes.ui.main.viewmodel.MainViewModel
import com.ayustark.itunes.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: MainRepository
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var author = " "
        if (intent != null) {
            author = intent?.getStringExtra("artist").toString()
        }
        setupUI()
        val dao = SearchDatabase.getInstance(application).searchDao
        setupViewModel(author, dao)
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as GridLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.getUsers().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
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
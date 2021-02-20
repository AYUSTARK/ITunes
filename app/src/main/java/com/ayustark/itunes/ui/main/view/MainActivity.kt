package com.ayustark.itunes.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayustark.itunes.R
import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.data.api.SongsApi
import com.ayustark.itunes.data.model.Result
import com.ayustark.itunes.ui.base.ViewModelFactory
import com.ayustark.itunes.ui.main.adapter.MainAdapter
import com.ayustark.itunes.ui.main.viewmodel.MainViewModel
import com.ayustark.itunes.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()
        //getList()
    }

/*
    private fun getList() {
        val songs = SongsApi.songsApiService.getList("Arjit")
            songs.enqueue(object: Callback<ResultApi> {
            override fun onResponse(call: Call<ResultApi>, response: Response<ResultApi>) {
                val song = response.body()
                if(song!=null) {
                    println("Here's the list: $song")
                    Log.d("Success...Yipee", song.toString())
                }
            }

            override fun onFailure(call: Call<ResultApi>, t: Throwable) {
                Log.d("Gaya kaam se", "Error ${t.toString()}", t)
            }
        })
    }
*/

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
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

    private fun renderList(users: List<Result>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(SongsApi),"Arijit")
        ).get(MainViewModel::class.java)
    }
}
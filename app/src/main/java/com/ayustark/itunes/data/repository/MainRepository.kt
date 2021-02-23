package com.ayustark.itunes.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ayustark.itunes.data.api.ApiHelper
import com.ayustark.itunes.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainRepository(
    private val apiHelper: ApiHelper,
    private val search: String,
    val dao: SearchDao
) {

    suspend fun getLists(
        users: MutableLiveData<Resource<List<SearchEntity>>>,
        viewModelScope: CoroutineScope,
    ) {
        val songs = dao.searchSongs(search)
        //println("Ye kyaa h? ${songs.size}")
        if (songs.isNotEmpty()) {
            //println("beda gark...sab barbaad...")
            users.postValue(Resource.success(songs))
            Log.d("DataBase Query", "Songs Queried")
        } else {
            //println("chalo aage bdho...Abhi ummid baaki h...")
            //println("This is $songs")
            return apiHelper.getList(
                search, users,
                object : ApiHelper.OnStatus {
                    override fun onSuccess(song: List<SearchEntity>) {
                        viewModelScope.launch {
                            //println("Ye kyaa h?? ${song.size}")
                            dao.clearSongs()
                            for (i in song) {
                                i.searchQuery = search
                                dao.insertSongs(i)
                                //println("checking $insert")
                            }

                            //println("Hoja plzz:- $song")
                            Log.d("DataBase Entry", "Song Inserted")
                        }
                    }

                    override fun onFailure() {
                        //println("Hoja plzz:- 12345")
                    }

                }
            )
        }
    }

/*
    suspend fun insert(searchResult:SearchEntity):Long{
        return dao.insertSongs(searchResult)
    }
*/

}
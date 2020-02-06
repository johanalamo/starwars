package com.alamo.starwars.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamo.starwars.data.model.Film
import com.alamo.starwars.data.model.FilmsResponse
import com.alamo.starwars.data.net.RetrofitClientFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmRepositoryImpl : FilmRepository {

    private val retrofitClient = RetrofitClientFilm()

    private val TAG = this::class.java.simpleName

    override fun getFilms(): LiveData<List<Film>?> {

        val data = MutableLiveData<List<Film>>()

        retrofitClient.getFilms().enqueue(object : Callback<FilmsResponse> {
            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                data.value = null
                Log.d(TAG, "onFailure: t -> " + t)
            }

            override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                data.value = response.body()?.results?.sortedBy { it.releaseDate }
                Log.d(TAG, "onResponse: respose.body()?.results -> ${response.body()?.results}")
            }
        })
        return data
    }
}

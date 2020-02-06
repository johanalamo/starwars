package com.alamo.starwars.data.net

import android.util.Log
import com.alamo.starwars.data.model.FilmsResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientFilm {

    private val filmsApi: FilmsApi

    private val TAG = this::class.java.simpleName

    companion object {
        private const val BASE_URL = "https://swapi.co/api/"
    }

    init {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        filmsApi = retrofit.create(FilmsApi::class.java)
    }

    fun getFilms(): Call<FilmsResponse> {
        return filmsApi.getFilms()
    }
}

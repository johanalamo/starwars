package com.alamo.starwars.data.net

import android.util.Log
import com.alamo.starwars.data.model.People
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientPeople {

    private val peopleApi: PeopleApi

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
        peopleApi = retrofit.create(PeopleApi::class.java)
    }

    fun getPeople(id: String): Call<People> {
        return peopleApi.getPeople(id)
    }
}

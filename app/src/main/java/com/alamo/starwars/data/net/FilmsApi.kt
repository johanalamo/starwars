package com.alamo.starwars.data.net

import com.alamo.starwars.data.model.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET

interface FilmsApi {

    @GET("films/")
    fun getFilms(): Call<FilmsResponse>
}

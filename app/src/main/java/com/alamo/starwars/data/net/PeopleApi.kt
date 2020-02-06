package com.alamo.starwars.data.net

import com.alamo.starwars.data.model.People
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleApi {

    @GET("people/{id}/")
    fun getPeople(@Path("id") id: String): Call<People>
}

package com.alamo.starwars.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alamo.starwars.data.model.People
import com.alamo.starwars.data.net.RetrofitClientPeople
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleRepositoryImpl : PeopleRepository {

    private val retrofitClient = RetrofitClientPeople()

    private val TAG = this::class.java.simpleName

    override fun getPeople(id: String): LiveData<People?> {
        val data = MutableLiveData<People>()


        retrofitClient.getPeople(id).enqueue(object : Callback<People> {
            override fun onFailure(call: Call<People>, t: Throwable) {
                data.value = null
                Log.d(TAG, "onFailure: t -> " + t)
            }

            override fun onResponse(call: Call<People>, response: Response<People>) {
                data.value = response.body()
                Log.d(TAG, "onResponse: respose.body()?.results -> ${response.body()}")
            }
        })
        return data
    }
}

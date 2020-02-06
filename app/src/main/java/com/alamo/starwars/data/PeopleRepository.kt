package com.alamo.starwars.data

import androidx.lifecycle.LiveData
import com.alamo.starwars.data.model.People

interface PeopleRepository {

    fun getPeople(id: String): LiveData<People?>
}

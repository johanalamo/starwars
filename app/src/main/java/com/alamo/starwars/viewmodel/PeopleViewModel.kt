package com.alamo.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alamo.starwars.data.PeopleRepository
import com.alamo.starwars.data.PeopleRepositoryImpl
import com.alamo.starwars.data.model.People

class PeopleViewModel(private val repository: PeopleRepository = PeopleRepositoryImpl()) :
    ViewModel() {

    fun getPeople(id: String): LiveData<People?> {
        return repository.getPeople(id)
    }
}

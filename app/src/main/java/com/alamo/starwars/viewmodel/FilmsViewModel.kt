package com.alamo.starwars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alamo.starwars.data.FilmRepository
import com.alamo.starwars.data.FilmRepositoryImpl
import com.alamo.starwars.data.model.Film

class FilmsViewModel(private val repository: FilmRepository = FilmRepositoryImpl()) : ViewModel() {

    fun getFilms(): LiveData<List<Film>?> {
        return repository.getFilms()
    }
}

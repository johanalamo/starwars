package com.alamo.starwars.data

import androidx.lifecycle.LiveData
import com.alamo.starwars.data.model.Film

interface FilmRepository {

    fun getFilms(): LiveData<List<Film>?>
}

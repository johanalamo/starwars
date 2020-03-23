package com.alamo.starwars.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alamo.starwars.R
import com.alamo.starwars.data.model.Film
import com.alamo.starwars.databinding.ItemFilmBinding
import kotlinx.android.synthetic.main.item_film.view.*

class FilmListAdapter(
    private val films: MutableList<Film>,
    private var listener: (Film) -> Unit
) : RecyclerView.Adapter<FilmListAdapter.FilmHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = DataBindingUtil.inflate<ItemFilmBinding>(layoutInflater,
            R.layout.item_film, parent, false)
        return FilmHolder(biding)
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        val film = films[position]
        holder.binding.film = film
        holder.binding.position = (position + 1).toString()
        holder.binding.root.setOnClickListener{ listener(films.get(position)) }
    }

    fun setFilms(filmList: List<Film>) {
        this.films.clear()
        this.films.addAll(filmList)
        notifyDataSetChanged()
    }

    inner class FilmHolder(val binding:ItemFilmBinding): RecyclerView.ViewHolder(binding.root)
}

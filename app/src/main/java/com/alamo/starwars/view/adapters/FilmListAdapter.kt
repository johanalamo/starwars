package com.alamo.starwars.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alamo.starwars.R
import com.alamo.starwars.data.model.Film
import kotlinx.android.synthetic.main.item_film.view.*

class FilmListAdapter(
    private val films: MutableList<Film>,
    private var listener: (Film) -> Unit
) : RecyclerView.Adapter<FilmListAdapter.FilmHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return FilmHolder(view)
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(films[position], position)
    }

    fun setFilms(filmList: List<Film>) {
        this.films.clear()
        this.films.addAll(filmList)
        notifyDataSetChanged()
    }

    inner class FilmHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(film: Film, position: Int) = with(view) {
            titleTextView.text = (position + 1).toString() + ". " + film.title
            releaseDateTextView.text = context.getString(R.string.releaseDate, film.releaseDate)
            directorTextView.text = context.getString(R.string.by, film.director)
            view.setOnClickListener { listener(films.get(position)) }
        }
    }
}

package com.alamo.starwars.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alamo.starwars.R
import com.alamo.starwars.data.model.Film
import com.alamo.starwars.view.adapters.FilmListAdapter
import com.alamo.starwars.viewmodel.FilmsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var viewModel: FilmsViewModel

    private var adapter = FilmListAdapter(mutableListOf()) { film -> showPeople(film) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        listRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listRecyclerView.adapter = adapter
        getFilms()
    }

    private fun getFilms() {
        showLoading()
        viewModel.getFilms().observe(this, Observer { films ->
            hideLoading()
            Log.d(TAG, "getFilms:  (films == null)? -> " + (films == null).toString())
            if (films == null) {
                showMessage(getString(R.string.network_error))
            } else {
                adapter.setFilms(films)
            }
        })
    }

    private fun showPeople(film: Film) {
        var intent = Intent(this, PeopleActivity::class.java)

        Log.d(TAG, "showPeople: film.title -> " + (film.title ?: "TITLE IS NULL"))

        intent.putExtra(INTENT_FILM_KEY, film)
        startActivity(intent)
    }

    private fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
        listRecyclerView.isEnabled = false
    }

    private fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
        listRecyclerView.isEnabled = true
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val INTENT_FILM_KEY = "film"
    }
}

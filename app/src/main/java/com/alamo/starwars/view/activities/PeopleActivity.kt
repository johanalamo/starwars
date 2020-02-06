package com.alamo.starwars.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alamo.starwars.R
import com.alamo.starwars.data.model.Film
import com.alamo.starwars.data.model.People
import com.alamo.starwars.view.adapters.PeopleListAdapter
import com.alamo.starwars.viewmodel.PeopleViewModel
import com.alamo.starwars.layoutmanager.AnimatedLinearLayoutManager
import kotlinx.android.synthetic.main.activity_people.*
import androidx.recyclerview.widget.LinearLayoutManager

fun getId(url: String): String {
    return url.substring(28, url.length - 1)
}

class PeopleActivity : AppCompatActivity() {

    private val TAG = PeopleActivity::class.java.simpleName

    private var film: Film? = null

    private var people = People()

    private lateinit var viewModel: PeopleViewModel

    private var adapter = PeopleListAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)

        film = intent.getParcelableExtra(MainActivity.INTENT_FILM_KEY)

        title = film?.title

        Log.d(TAG, "onCreate: film.title -> " + (film?.title ?: "TITLE IS NULL")
                    + "\nonCreate: film.characters.size -> " + (film?.characters?.size ?: "CHARACTERS IS NULL"))

        viewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)
        listRecyclerView.layoutManager = AnimatedLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listRecyclerView.adapter = adapter
        getPeople()
    }

    private fun getPeople() {
        showLoading()
        var total:Int = film?.characters?.size ?: 0
        var charged = 0

        var list:List<People> = mutableListOf()

        for (url in film?.characters!!) {
            var id = getId(url)

            viewModel.getPeople(id).observe(this, Observer { people ->
                showLoading()
                charged++
                var isLast:Boolean = (charged>=total)
                if (people == null) {
                    Log.d(TAG, "getPeople: people IS NULL")
                } else {
                    Log.d(TAG, "getPeople: people.name -> " + (people.name ?: "NAME IS NULL") + ( if (isLast) " (last)" else "" ))
                    list += people
                    if (isLast) {
                        adapter.setPeople(list)
                        hideLoading()
                    }
                }
            })
        }
    }

    private fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
        listRecyclerView.isEnabled = false
    }

    private fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
        listRecyclerView.isEnabled = true
    }
}

package com.alamo.starwars.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alamo.starwars.R
import com.alamo.starwars.data.model.People
import kotlinx.android.synthetic.main.item_people.view.*

class PeopleListAdapter(
    private val people: MutableList<People>
) : RecyclerView.Adapter<PeopleListAdapter.PeopleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_people, parent, false)
        return PeopleHolder(view)
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        holder.bind(people[position], position)
    }

    fun setPeople(peopleList: List<People>) {
        this.people.clear()
        this.people.addAll(peopleList)
        notifyDataSetChanged()
    }

    inner class PeopleHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(people: People, position: Int) = with(view) {
            nameTextView.text = (position + 1).toString() + ". " + people.name
            birthYearTextView.text = context.getString(R.string.birthYear, people.birthYear)
            genderTextView.text = context.getString(R.string.gender, people.gender)
        }
    }
}

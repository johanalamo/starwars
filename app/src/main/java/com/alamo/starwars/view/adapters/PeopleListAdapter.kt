package com.alamo.starwars.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alamo.starwars.R
import com.alamo.starwars.data.model.People
import com.alamo.starwars.databinding.ItemPeopleBinding
import kotlinx.android.synthetic.main.item_people.view.*

class PeopleListAdapter(
    private val people: MutableList<People>
) : RecyclerView.Adapter<PeopleListAdapter.PeopleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPeopleBinding>(layoutInflater,
            R.layout.item_people, parent, false)
        return PeopleHolder(binding)
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: PeopleHolder, position: Int) {
        val person = people[position]
        holder.binding.person = person
        holder.binding.position = (position + 1).toString()
    }

    fun setPeople(peopleList: List<People>) {
        this.people.clear()
        this.people.addAll(peopleList)
        notifyDataSetChanged()
    }

    inner class PeopleHolder(val binding:ItemPeopleBinding): RecyclerView.ViewHolder(binding.root)
}

package com.odinn.rickandmortyapp.screens.personlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.odinn.rickandmortyapp.R
import com.odinn.rickandmortyapp.model.Result
import com.odinn.rickandmortyapp.screens.savedpersons.PersonSavedFragment
import kotlinx.android.synthetic.main.item_person.view.*

class PersonListAdapter:RecyclerView.Adapter<PersonListAdapter.PersonViewHolder>() {
    private var listPersons:List<Result> = emptyList()

    class PersonViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        with(holder.itemView){
            txt_person_name.text = listPersons[position].name
            txt_person_status.text = "${listPersons[position].status} - ${listPersons[position].species}"
            if (listPersons[position].status == "Alive"){
                img_status_light.setImageResource(R.drawable.ic_circle_green)
            } else{
                img_status_light.setImageResource(R.drawable.ic_circle_red)
            }
            txt_last_known_loc.text = listPersons[position].location?.name
            Glide.with(context)
                .load(listPersons[position].image)
                .centerCrop()
                .placeholder(R.drawable.ic_foto)
                .into(img_person)
        }

    }

    override fun getItemCount(): Int = listPersons.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<Result>){
        listPersons = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: PersonViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            PersonListFragment.clickPerson(listPersons[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: PersonViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener { null }
    }
}
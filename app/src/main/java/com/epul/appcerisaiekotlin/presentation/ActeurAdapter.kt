package com.epul.appcerisaiekotlin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.domain.Acteur
import com.epul.appcerisaiekotlin.domain.Film

class ActeurAdapter(
    var items: List<Acteur>,
    private val onMoviesClick: OnMoviesClick,
    private val onCharatcerClick: OnCharatcerClick
) : ListAdapter<Acteur, ActorViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_actors, parent, false)
        return ActorViewHolder(view, onMoviesClick, onCharatcerClick)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}

class ItemDiffUtil : DiffUtil.ItemCallback<Acteur>() {
    override fun areItemsTheSame(oldItem: Acteur, newItem: Acteur) = false

    override fun areContentsTheSame(oldItem: Acteur, newItem: Acteur) = false
}

typealias OnMoviesClick = (Acteur) -> Unit
typealias OnCharatcerClick = (Acteur) -> Unit
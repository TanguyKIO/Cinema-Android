package com.epul.appcerisaiekotlin.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.domain.Acteur
import com.epul.appcerisaiekotlin.domain.Film

class FilmAdapter(
    var items: List<Film>,
    private val onMoviesClicker: OnMoviesClicker
) : ListAdapter<Film, FilmViewHolder>(FilmDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view, onMoviesClicker)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}

class FilmDiffUtil : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film) = false

    override fun areContentsTheSame(oldItem: Film, newItem: Film) = false
}

typealias OnMoviesClicker = (Film) -> Unit
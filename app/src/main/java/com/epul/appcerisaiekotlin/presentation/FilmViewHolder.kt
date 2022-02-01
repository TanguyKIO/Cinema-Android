package com.epul.appcerisaiekotlin.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.domain.Film

class FilmViewHolder(private val view: View,private val onMoviesClicker: OnMoviesClicker): RecyclerView.ViewHolder(view) {


    private val titre: TextView = view.findViewById(R.id.title_film)
    private val text1: TextView = view.findViewById(R.id.date_sortie)

    fun bind(film: Film) {
        titre.text = film.titre
        text1.text = film.dateSortie
        view.setOnClickListener {
            onMoviesClicker(film)
        }
    }
}
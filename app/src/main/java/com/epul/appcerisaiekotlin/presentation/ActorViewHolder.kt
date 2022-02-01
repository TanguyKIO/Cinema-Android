package com.epul.appcerisaiekotlin.presentation

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.domain.Acteur

class ActorViewHolder(view: View, private val onMoviesClick: OnMoviesClick, private val onCharatcerClick: OnCharatcerClick): RecyclerView.ViewHolder(view) {


    private val nom: TextView = view.findViewById(R.id.nom_acteur)
    private val date: TextView = view.findViewById(R.id.naissance)
    private val movie: TextView = view.findViewById(R.id.movies)
    private val char: TextView = view.findViewById(R.id.characters)

    fun bind(acteur: Acteur) {
        nom.text = acteur.nom
        date.text = acteur.naissance
        movie.setOnClickListener {
            acteur.let { it1 -> onMoviesClick(it1) }
        }
        char.setOnClickListener {
            acteur.let { it1 -> onCharatcerClick(it1) }
        }
    }
}
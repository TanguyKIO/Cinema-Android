package com.epul.appcerisaiekotlin.presentation

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.domain.Personnage
import com.example.triplekaisse.presentation.item.OnDeleteClickListener

class BasicViewHolder(view: View, private val onDeleteClickListener: OnDeleteClickListener, private val onEditClickListener: OnDeleteClickListener,private val isAdmin: Boolean): RecyclerView.ViewHolder(view) {


    private val titre: TextView = view.findViewById(R.id.title_t)
    private val text1: TextView = view.findViewById(R.id.text1)
    private val text2: TextView = view.findViewById(R.id.text2)
    private val suppr: ImageButton = view.findViewById(R.id.suppr)
    private val edit: ImageButton = view.findViewById(R.id.edit)

    fun bind(personnage: Personnage) {
        titre.text = personnage.nomPers
        text1.text = personnage.noAct.toString()
        text2.text = personnage.noFilm.toString()
        if(isAdmin) {
            suppr.visibility = View.VISIBLE
            edit.visibility = View.VISIBLE
        } else {
            suppr.visibility = View.INVISIBLE
            edit.visibility = View.INVISIBLE
        }
        edit.setOnClickListener {
            personnage.let { it1 -> onEditClickListener(it1) }
        }
        suppr.setOnClickListener {
            personnage.let { it1 -> onDeleteClickListener(it1) }
        }
    }
}
package com.example.triplekaisse.presentation.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.domain.Personnage
import com.epul.appcerisaiekotlin.presentation.BasicViewHolder

class BasicAdapter(
    var items: List<Personnage>,
    private val onDeleteClickListener: OnDeleteClickListener,
    private val onEditClickListener: OnEditClickListener,
    private val isAdmin: Boolean,
) : ListAdapter<Personnage, BasicViewHolder>(CharDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_basic, parent, false)
        return BasicViewHolder(view, onDeleteClickListener, onEditClickListener, isAdmin)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}

class CharDiffUtil : DiffUtil.ItemCallback<Personnage>() {
    override fun areItemsTheSame(oldItem: Personnage, newItem: Personnage) = false

    override fun areContentsTheSame(oldItem: Personnage, newItem: Personnage) = false
}

typealias OnDeleteClickListener = (Personnage) -> Unit
typealias OnEditClickListener = (Personnage) -> Unit
package com.epul.appcerisaiekotlin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.databinding.FramgentActorsBinding
import com.epul.appcerisaiekotlin.domain.Acteur
import com.epul.appcerisaiekotlin.domain.Film
import com.epul.appcerisaiekotlin.domain.Personnage

class ActorFragment : Fragment() {

    private val viewModel: ActeurViewModel by viewModels()

    private var _binding: FramgentActorsBinding? = null
    private val binding get() = _binding!!

    private val items: MutableList<Acteur> = mutableListOf()

    private val adapter = ActeurAdapter(items,::onMoviesClick,::onCharacterClick)

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FramgentActorsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.acteurs
        recyclerView.adapter = adapter
        viewManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = viewManager

        viewModel.getActors()

        viewModel.response.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun onCharacterClick(acteur: Acteur) {
        val bundle = bundleOf("id" to acteur.id)
        Navigation.findNavController(binding.root).navigate(R.id.action_acteur_to_perso, bundle)
    }

    private fun onMoviesClick(acteur: Acteur) {
        val bundle = bundleOf("id" to acteur.id)
        Navigation.findNavController(binding.root).navigate(R.id.action_acteur_to_film, bundle)
    }

}
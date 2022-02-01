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
import com.epul.appcerisaiekotlin.databinding.FragmentFilmBinding
import com.epul.appcerisaiekotlin.domain.Film


class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null

    private val viewModel: FilmViewModel by viewModels()

    private val binding get() = _binding!!

    private val items: MutableList<Film> = mutableListOf()

    private val adapter = FilmAdapter(items, ::onMovieClicker)

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.films
        recyclerView.adapter = adapter
        viewManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = viewManager

        val id = arguments?.getInt("id")

        if(id==null) {
            viewModel.getFilms()
        } else {
           viewModel.getFilmsByAct(id)
        }
        viewModel.response.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
    }

    fun onMovieClicker(film:Film) {
        val bundle = bundleOf("film" to film)
        Navigation.findNavController(binding.root).navigate(R.id.action_listFilm_to_film, bundle)
    }
}
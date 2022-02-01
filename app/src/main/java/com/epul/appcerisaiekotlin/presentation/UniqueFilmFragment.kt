package com.epul.appcerisaiekotlin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.epul.appcerisaiekotlin.databinding.FragmentUniqueFilmBinding
import com.epul.appcerisaiekotlin.domain.Film

class UniqueFilmFragment : Fragment() {

    private val viewModel: UniqueFilmViewModel by viewModels()

    private var _binding: FragmentUniqueFilmBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUniqueFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val film = arguments?.get("film") as Film

        viewModel.getReal(film.noRea)

        binding.catText.text = film.codeCat
        binding.title.text = film.titre
        binding.dateText.text = film.dateSortie
        binding.lengthText.text = film.duree.toString() + "min"
        binding.moneyText.text = film.recette.toString() + "€"
        binding.noteText.text = film.note.toString() + "/10"
        binding.descriptionText.text = film.description
        viewModel.response.observe(viewLifecycleOwner) {
            if(it!=null) {
                binding.reaText.text = it.prenom + " " + it.nom
            } else {
                Toast.makeText(context,"Erreur réalisateur", Toast.LENGTH_SHORT).show()
            }

        }

    }

}
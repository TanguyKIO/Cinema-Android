package com.epul.appcerisaiekotlin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.epul.appcerisaiekotlin.R
import com.epul.appcerisaiekotlin.databinding.FragmentEditBinding
import com.epul.appcerisaiekotlin.domain.Personnage

class EditFramgent : Fragment() {

    private val viewModel: EditViewModel by viewModels()

    private var _binding: FragmentEditBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val perso = arguments?.get("perso") as Personnage?


        if(perso!=null) {
            binding.filmEdit.setText(perso.noFilm.toString())
            binding.actorEdit.setText(perso.noAct.toString())
            binding.nameEdit.setText(perso.nomPers)
        }


        binding.add.setOnClickListener {
            val film =binding.filmEdit.text.toString()
            val acteur =binding.actorEdit.text.toString()
            val name =binding.nameEdit.text.toString()

            if(name.isEmpty() || film.isEmpty() || acteur.isEmpty()){
                Toast.makeText(
                    requireContext(),
                    "Formulaire incorrect",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if(perso!=null) {
                    viewModel.editCharacter(Personnage(film.toInt(),acteur.toInt(),name))
                } else {
                    viewModel.addCharacter(Personnage(film.toInt(),acteur.toInt(),name))
                }
                Navigation.findNavController(binding.root).navigate(R.id.action_editChar_to_char)
            }
        }

        viewModel.response.observe(viewLifecycleOwner) {
            if(it) {
                Toast.makeText(
                    context,
                    "Modification réussie",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Modification échouée",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
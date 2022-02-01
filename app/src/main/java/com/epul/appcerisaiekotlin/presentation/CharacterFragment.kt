package com.epul.appcerisaiekotlin.presentation

import android.content.Context
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
import com.epul.appcerisaiekotlin.config.MyConstants.USER_ROLE
import com.epul.appcerisaiekotlin.databinding.FragmentCharacterBinding
import com.epul.appcerisaiekotlin.domain.Personnage
import com.example.triplekaisse.presentation.item.BasicAdapter

class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null

    private val viewModel: CharacterViewModel by viewModels()

    private val binding get() = _binding!!

    private val items: MutableList<Personnage> = mutableListOf()

    private lateinit var adapter: BasicAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireActivity().getSharedPreferences(
            requireActivity().getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val admin = prefs.getString(USER_ROLE, null)
        val isAdmin = admin == "admin"

        adapter = BasicAdapter(items, ::onDeleteClick, ::onEditClick, isAdmin)

        recyclerView = binding.personnages
        recyclerView.adapter = adapter
        viewManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = viewManager
    }

    override fun onResume() {
        super.onResume()
        val id = arguments?.getInt("id")

        if (id == null) {
            viewModel.getCharacters()
        } else {
            viewModel.getCharactersByAct(id)
        }

        viewModel.response.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        binding.button.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_char_to_editChar)
        }
    }

    private fun onEditClick(personnage: Personnage) {
        val bundle = bundleOf("perso" to personnage)
        Navigation.findNavController(binding.root).navigate(R.id.action_char_to_editChar, bundle)
    }

    private fun onDeleteClick(personnage: Personnage) {
        viewModel.delete(personnage)
    }

}
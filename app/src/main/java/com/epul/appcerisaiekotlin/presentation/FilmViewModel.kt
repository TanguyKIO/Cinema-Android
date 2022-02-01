package com.epul.appcerisaiekotlin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epul.appcerisaiekotlin.domain.Film
import com.epul.appcerisaiekotlin.service.CinemaAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FilmViewModel: ViewModel() {

    private val _response = MutableLiveData<List<Film>>()

    val response: LiveData<List<Film>>
        get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    fun getFilms() {
        coroutineScope.launch {
            val getObjects = CinemaAPI.cinemaService.getFilmsAsync()
            try {
                val listResult = getObjects.await()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = ArrayList()
            }
        }
    }

    fun getFilmsByAct(id: Int) {
        coroutineScope.launch {
            val getObjects = CinemaAPI.cinemaService.getActFilmAsync(id)
            try {
                val listResult = getObjects.await()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = ArrayList()
            }
        }
    }
}
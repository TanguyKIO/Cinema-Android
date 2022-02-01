package com.epul.appcerisaiekotlin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epul.appcerisaiekotlin.domain.Realisateur
import com.epul.appcerisaiekotlin.service.CinemaAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UniqueFilmViewModel: ViewModel() {
    private val _response = MutableLiveData<Realisateur?>()

    val response: LiveData<Realisateur?>
        get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    fun getReal(id: Int) {
        coroutineScope.launch {
            val getObjects = CinemaAPI.cinemaService.getRealisateurAsync(id)
            try {
                val real = getObjects.await()
                _response.value = real
            } catch (e: Exception) {
                _response.value = null
            }
        }
    }
}
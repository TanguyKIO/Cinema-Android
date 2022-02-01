package com.epul.appcerisaiekotlin.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epul.appcerisaiekotlin.domain.Acteur
import com.epul.appcerisaiekotlin.service.CinemaAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ActeurViewModel: ViewModel() {

    private val _response = MutableLiveData<List<Acteur>>()

    val response: LiveData<List<Acteur>>
        get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    fun getActors() {
        coroutineScope.launch {
            val getObjects = CinemaAPI.cinemaService.getActeurAsync()
            try {
                val listResult = getObjects.await()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = ArrayList()
            }
        }
    }
}
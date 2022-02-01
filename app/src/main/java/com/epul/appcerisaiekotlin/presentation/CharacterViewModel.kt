package com.epul.appcerisaiekotlin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epul.appcerisaiekotlin.domain.Personnage
import com.epul.appcerisaiekotlin.service.CinemaAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel: ViewModel() {

    private val _response = MutableLiveData<List<Personnage>>()

    val response: LiveData<List<Personnage>>
        get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    fun getCharacters() {
        coroutineScope.launch {
            val getObjects = CinemaAPI.cinemaService.getCharacterAsync()
            try {
                val listResult = getObjects.await()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = ArrayList()
            }
        }
    }

    fun getCharactersByAct(id: Int) {
        coroutineScope.launch {
            val getObjects = CinemaAPI.cinemaService.getActCharAsync(id)
            try {
                val listResult = getObjects.await()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = ArrayList()
            }
        }
    }

    fun delete(personnage: Personnage) {
        coroutineScope.launch {
            CinemaAPI.cinemaService.deletePerso(personnage.noFilm, personnage.noAct).enqueue(object :
                Callback<Object> {
                override fun onResponse(call: Call<Object>, uneReponse: Response<Object>) {
                    getCharacters()
                }
                override fun onFailure(call: Call<Object>, t: Throwable) {
                    getCharacters()
                }
            })
            getCharacters()
        }
    }
}
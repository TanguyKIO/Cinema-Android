package com.epul.appcerisaiekotlin.presentation


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epul.appcerisaiekotlin.domain.Film
import com.epul.appcerisaiekotlin.domain.Personnage
import com.epul.appcerisaiekotlin.service.CinemaAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditViewModel : ViewModel() {

    private val _response = MutableLiveData<Boolean>()

    val response: LiveData<Boolean>
        get() = _response

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun editCharacter(perso: Personnage) {
        coroutineScope.launch {
            CinemaAPI.cinemaService.postCharacter(perso).enqueue(object : Callback<Object> {
                override fun onResponse(call: Call<Object>, uneReponse: Response<Object>) {
                    _response.value=true
                }

                override fun onFailure(call: Call<Object>, t: Throwable) {
                    _response.value=false
                }
            })
        }
    }

    fun addCharacter(personnage: Personnage) {
        coroutineScope.launch {
            CinemaAPI.cinemaService.addCharacter(personnage).enqueue(object : Callback<Object> {
                override fun onResponse(call: Call<Object>, uneReponse: Response<Object>) {
                    _response.value=true
                }

                override fun onFailure(call: Call<Object>, t: Throwable) {
                    _response.value=false
                }
            })
        }
    }

}
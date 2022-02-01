package com.epul.appcerisaiekotlin.service

import com.epul.appcerisaiekotlin.domain.LoginParam
import com.epul.appcerisaiekotlin.domain.Utilisateur
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST



interface IntConnexion {

    @POST("authentification/login")
    fun getConnexion(@Body unL: LoginParam): Call<Object>

}
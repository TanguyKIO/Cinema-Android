package com.epul.appcerisaiekotlin.service

import com.epul.appcerisaiekotlin.config.MyConstants
import com.epul.appcerisaiekotlin.domain.Acteur
import com.epul.appcerisaiekotlin.domain.Personnage
import com.epul.appcerisaiekotlin.domain.Film
import com.epul.appcerisaiekotlin.domain.Realisateur
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

interface ServiceCinema {

    @GET("film/getFilms")
    fun getFilmsAsync(): Deferred<List<Film>>

    @GET("acteur/getActeurs")
    fun getActeurAsync(): Deferred<List<Acteur>>

    @GET("personnage/getPersonnages")
    fun getCharacterAsync(): Deferred<List<Personnage>>

    @GET("film/getFilmsByActeur/{id}")
    fun getActFilmAsync(@Path("id") id: Int): Deferred<List<Film>>

    @GET("personnage/getPersonnagesByAct/{id}")
    fun getActCharAsync(@Path("id") id: Int): Deferred<List<Personnage>>

    @GET("realisateur/getRealById/{id}")
    fun getRealisateurAsync(@Path("id") id: Int): Deferred<Realisateur>

    @POST("personnage/modification")
    fun postCharacter(@Body personnage: Personnage): Call<Object>

    @DELETE("personnage/delete/{idFilm}/{idAct}")
    fun deletePerso(@Path("idFilm") idFilm: Int,@Path("idAct") idAct: Int): Call<Object>

    @POST("personnage/ajout")
    fun addCharacter(@Body personnage: Personnage): Call<Object>

}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(MyConstants.url)
    .build()

object CinemaAPI {
    val cinemaService : ServiceCinema by lazy { retrofit.create(ServiceCinema::class.java) }
}

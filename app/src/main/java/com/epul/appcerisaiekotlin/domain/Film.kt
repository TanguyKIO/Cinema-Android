package com.epul.appcerisaiekotlin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val titre: String,
    val duree: Int,
    val dateSortie: String,
    val budget: Int,
    val recette: Int,
    val noRea: Int,
    val codeCat: String,
    val image: String,
    val note: Float,
    val description: String
) : Parcelable
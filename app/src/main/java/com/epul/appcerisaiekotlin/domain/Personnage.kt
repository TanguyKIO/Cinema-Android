package com.epul.appcerisaiekotlin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Personnage(
    val noFilm: Int,
    val noAct: Int,
    val nomPers: String
) : Parcelable

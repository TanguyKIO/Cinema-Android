package com.epul.appcerisaiekotlin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Acteur(
    val id: Int,
    val nom: String,
    val prenom: String,
    val naissance: String,
    val deces: String?,
) : Parcelable
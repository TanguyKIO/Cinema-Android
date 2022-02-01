package com.epul.appcerisaiekotlin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Realisateur(val noRea: Int, val nom: String, val prenom: String): Parcelable
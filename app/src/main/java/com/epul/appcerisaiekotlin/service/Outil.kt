package com.epul.appcerisaiekotlin.service

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by christian on 18/01/2017.
 */
object Outil {
    ///
// On convertit une date en chaîne
///
    fun DateToString(d: Date): String { // on crée son propre format
        val dateFormatpers: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        // on lance la conversion
        return dateFormatpers.format(d)
    }

    ///
// On convertit une chaîne en date
///
    fun chaineTodate(s: String): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        var   unedate: Date =Date()
        try {
            unedate = formatter.parse(s) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return unedate
    }
}
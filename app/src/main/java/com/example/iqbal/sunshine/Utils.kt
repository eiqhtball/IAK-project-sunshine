package com.example.iqbal.sunshine

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by iqbal on 28/01/18.
 */

fun convertDataToWeekDay(date: String?): String? {
    /*konversi tanggal bertipe string menjadi tanggal bertipe Date*/
    val oldSdf = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
    val oldDate: Date = oldSdf.parse(date)

    /*konversi tgl Date ke nama hari*/
    val daySdf = SimpleDateFormat("EEEE", Locale("id"))
    val day = daySdf.format(oldDate)

    println(day)
    return day
}

fun getIcon(weatherId: Int?): Int{
    return when(weatherId){
        in 200..232     -> R.drawable.ic_storm
        in 300..321     -> R.drawable.ic_light_rain
        in 500..504     -> R.drawable.ic_rain
        511             -> R.drawable.ic_snow
        in 520..531     -> R.drawable.ic_rain
        in 600..622     -> R.drawable.ic_snow
        in 701..760     -> R.drawable.ic_fog
        761, 771, 781   -> R.drawable.ic_storm
        800             -> R.drawable.ic_clear
        801             -> R.drawable.ic_light_clouds
        in 802..804     -> R.drawable.ic_cloudy
        in 900..906     -> R.drawable.ic_storm
        in 958..962     -> R.drawable.ic_storm
        in 951..957     -> R.drawable.ic_clear
        else            -> R.drawable.ic_storm
    }
}
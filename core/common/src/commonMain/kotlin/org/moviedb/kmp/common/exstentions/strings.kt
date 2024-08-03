package org.moviedb.kmp.common.exstentions

import co.touchlab.kermit.Logger
import kotlinx.datetime.LocalDateTime

fun String.toDate(): LocalDateTime?{
    return try {
        LocalDateTime.parse(this)
    }catch (e:Exception){
        Logger.e("ToDate", e)
        null
    }

}
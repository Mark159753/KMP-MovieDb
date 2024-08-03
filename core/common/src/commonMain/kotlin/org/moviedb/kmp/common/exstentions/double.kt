package org.moviedb.kmp.common.exstentions

import kotlin.math.round

fun Double.roundToRating() = round(this * 10) / 10.0
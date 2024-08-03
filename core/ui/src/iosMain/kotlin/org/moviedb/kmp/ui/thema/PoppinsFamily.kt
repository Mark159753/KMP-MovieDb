package org.moviedb.kmp.ui.thema

import dev.icerock.moko.resources.uiFont
import org.moviedb.kmp.ui.MR


object PoppinsFamily{
    fun semiBold(withSize:Double) = MR.fonts.poppins_semi_bold.uiFont(withSize)
    fun bold(withSize:Double) = MR.fonts.poppins_bold.uiFont(withSize)
    fun light(withSize:Double) = MR.fonts.poppins_light.uiFont(withSize)
    fun medium(withSize:Double) = MR.fonts.poppins_medium.uiFont(withSize)
    fun regular(withSize:Double) = MR.fonts.poppins_regular.uiFont(withSize)
}
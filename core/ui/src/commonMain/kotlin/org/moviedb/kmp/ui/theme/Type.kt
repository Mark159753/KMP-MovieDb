package org.moviedb.kmp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.asFont
import org.moviedb.kmp.ui.MR


@Immutable
class AppTypography {

    private val poppinsFontFamily
        @Composable
        get() = FontFamily(
            MR.fonts.poppins_regular.asFont(FontWeight.Normal, FontStyle.Normal)!!,
            MR.fonts.poppins_bold.asFont(FontWeight.Bold, FontStyle.Normal)!!,
            MR.fonts.poppins_medium.asFont(FontWeight.Medium, FontStyle.Normal)!!,
            MR.fonts.poppins_semi_bold.asFont(FontWeight.SemiBold, FontStyle.Normal)!!,
            MR.fonts.poppins_light.asFont(FontWeight.Light, FontStyle.Normal)!!,
        )

    val h0: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

    val h1: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 24.sp
        )

    val h2: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 20.sp
        )

    val h3: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )

    val labelRegular: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )

    val labelMedium: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 12.sp
        )

    val labelButton: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

    val tertiaryButton: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

    val linkMedium: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )

    val linkSmall: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )

    val labelText: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )

    val inputText: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )

    val supportText: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )

    val bodyLarge: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp
        )

    val bodyMedium: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 18.sp
        )

    val bodySmall: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )

    val caption: TextStyle
        @Composable
        get() = TextStyle(
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp
        )
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }

val MaterialTheme.localTypography: AppTypography
    @Composable
    get() = LocalTypography.current

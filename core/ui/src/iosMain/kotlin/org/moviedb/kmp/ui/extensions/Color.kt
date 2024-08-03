package org.moviedb.kmp.ui.extensions

import androidx.compose.ui.graphics.Color
import platform.UIKit.UIColor
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.colorWithDynamicProvider


internal fun createDynamicUIColor(light:Color, dark:Color):UIColor{
    return UIColor.colorWithDynamicProvider { traits ->
        if (traits?.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark){
            dark.toUiColor()
        }else{
            light.toUiColor()
        }
    }
}

fun Color.toUiColor():UIColor{
    return UIColor(
        red = this.red.toDouble(),
        green = this. green.toDouble(),
        blue = this.blue.toDouble(),
        alpha = this.alpha.toDouble()
    )
}
package org.moviedb.kmp.ui.extensions

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

fun getLocalizedString(res:StringResource) = res.desc().localized()

fun getLocalizedString(res:StringResource, lng:String = "en"):String{
    val fallbackLocaleBundle = StringDesc.LocaleType
        .Custom(lng)
        .getLocaleBundle(res.bundle)
    return fallbackLocaleBundle.localizedStringForKey(
        key = res.resourceId,
        value = null,
        table = null
    )
}


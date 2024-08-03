package org.moviedb.kmp.domain.actions

sealed interface Actions {

    data object OnNavBack:Actions
    data class Error(val e:DisplayError):Actions

}

sealed interface ExploreActions:Actions{
    data object Retry:ExploreActions
}

sealed interface DisplayError{
    data object ApiParse:DisplayError
    data object Connectivity:DisplayError
    data class Message(val msg:String):DisplayError
}
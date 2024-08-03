package org.moviedb.kmp.domain.states.explore.filter

interface SaveableFilter<T, S> {

    fun initialize()

    fun onGetSavedValue():T?

    fun saveAndApplyFilter():S?

    fun clear()
}
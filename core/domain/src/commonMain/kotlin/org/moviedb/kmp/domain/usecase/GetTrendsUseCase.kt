package org.moviedb.kmp.domain.usecase

import kotlinx.coroutines.flow.map
import org.moviedb.kmp.data.repositories.trends.TrendsRepository

class GetTrendsUseCase(
    private val trendsRepository: TrendsRepository,
) {

    operator fun invoke(
        take:Int = 10
    ) = trendsRepository
        .allTrending
        .map { list -> list.take(take) }
}
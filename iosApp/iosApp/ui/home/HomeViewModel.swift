//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 02.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class HomeViewModel: ObservableObject{
    
    private let moviesRepository:MoviesListRepository
    private let homeState:HomeState
    
    @Published
    var trendsList:[TrendingModel] = []
    
    
    //Upcoming section
    @Published
    var upcomingMoviesSize:Int = 0
    
    var upcomingLazyPaging:IOSLazyPagingItems<MovieModel>? = nil
    
    //TV trends section
    var tvTrendsSize:Int = 0
    
    var tvTrendsLazyPaging:IOSLazyPagingItems<MovieModel>? = nil
    
    //Trending People section
    var trendingPeopleSize:Int = 0
    
    var trendingPeopleLazyPaging:IOSLazyPagingItems<PersonModel>? = nil
    

    
    var wathers:[Closeable] = []
    
    init(
        moviesRepository: MoviesListRepository,
        homeState:HomeState
    ) {
        self.moviesRepository = moviesRepository
        self.homeState = homeState
        observeTrendsList()
        observeUpcomingMoviesList()
        observeTvTrendsList()
        observeTrendingPeopleList()
    }
    
    private func observeTrendsList(){
        let watcher = CFlowKt.wrap(homeState.trendsList).watch(block: { list in
            self.trendsList = list as! [TrendingModel]
        })
        wathers.append(watcher)
    }
    
    private func observeUpcomingMoviesList(){
        let watcher = IOSLazyPagingItemsKt.collectAsIOSLazyPagingItems(
            homeState.upcomingMovies,
            block: { lazyPaging in
                let paging = lazyPaging as! IOSLazyPagingItems<MovieModel>
                self.upcomingLazyPaging = paging
            },
            itemCount: { size in
                self.upcomingMoviesSize = Int(truncating: size)
            },
            loadStates: {_ in }
        )
        
        wathers.append(watcher)
    }
    
    private func observeTvTrendsList(){
        let watcher = IOSLazyPagingItemsKt.collectAsIOSLazyPagingItems(
            homeState.tvTrending,
            block: { lazyPaging in
                let paging = lazyPaging as! IOSLazyPagingItems<MovieModel>
                self.tvTrendsLazyPaging = paging
            },
            itemCount: { size in
                self.tvTrendsSize = Int(truncating: size)
            },
            loadStates: {_ in }
        )
        
        wathers.append(watcher)
    }   
    
    private func observeTrendingPeopleList(){
        let watcher = IOSLazyPagingItemsKt.collectAsIOSLazyPagingItems(
            homeState.trendingPeople,
            block: { lazyPaging in
                let paging = lazyPaging as! IOSLazyPagingItems<PersonModel>
                self.trendingPeopleLazyPaging = paging
            },
            itemCount: { size in
                self.trendingPeopleSize = Int(truncating: size)
            },
            loadStates: {_ in }
        )
        
        wathers.append(watcher)
    }
        
    
    deinit{
        wathers.forEach{ closeble in closeble.close() }
    }
    
    
}

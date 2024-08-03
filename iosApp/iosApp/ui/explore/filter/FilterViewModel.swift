//
//  FilterViewModel.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared


class FilterViewModel:ObservableObject{
    
    let state:ExploreFilterState
    
    @Published
    var contentType:ContentType = ContentType.movie

    @Published
    var sortBy:SortFilterItem
    
    @Published
    var useReleaseYearFilter:Bool = false
    @Published
    var selectedYear:Int = 2010
    
    @Published
    var genres:[SortFilterItem] = []
    @Published
    var selectedGenres:Set<SortFilterItem> = []
    
    var discoverSize:Int = 0
    var discoverLazyPaging:IOSLazyPagingItems<MovieModel>? = nil
    
    @Published
    var selectedFilters:[SortFilterItem] = []
    
    var wathers:[Closeable] = []
    
    init(state: ExploreFilterState) {
        self.state = state
        sortBy = state.sortState.sortOptions.first!
        observeContentType()
        observeSortBy()
        observeUseReleaseYear()
        observeSelectedYear()
        observeGenres()
        observeSelectedGenres()
        observeDiscoverList()
        observeSelectedFilters()
        observeActions()
    }
    
    private func observeContentType(){
        let watcher = CFlowKt.wrap(state.contentTypeState.contentType).watch(block: { type in
            self.contentType = type as! ContentType
        })
        wathers.append(watcher)
    }
    
    private func observeSortBy(){
        let watcher = CFlowKt.wrap(state.sortState.sortBy).watch(block: { sort in
            self.sortBy = sort as! SortFilterItem
        })
        wathers.append(watcher)
    }
    
    private func observeUseReleaseYear(){
        let watcher = CFlowKt.wrap(state.releaseYearStateFilter.useReleaseYearFilter).watch(block: { use in
            self.useReleaseYearFilter = use as! Bool
        })
        wathers.append(watcher)
    }
    
    private func observeSelectedYear(){
        let watcher = CFlowKt.wrap(state.releaseYearStateFilter.releaseYear).watch(block: { year in
            self.selectedYear = year as! Int
        })
        wathers.append(watcher)
    }
    
    private func observeGenres(){
        let watcher = CFlowKt.wrap(state.genreState.genres).watch(block: { g in
            self.genres = g as! [SortFilterItem]
        })
        wathers.append(watcher)
    }
    
    private func observeSelectedGenres(){
        let watcher = CFlowKt.wrap(state.genreState.selectedGenres).watch(block: { g in
            self.selectedGenres = g as! Set<SortFilterItem>
        })
        wathers.append(watcher)
    }
    
    private func observeDiscoverList(){
        let watcher = IOSLazyPagingItemsKt.collectAsIOSLazyPagingItems(
            state.pagingItems,
            block: { lazyPaging in
                let paging = lazyPaging as! IOSLazyPagingItems<MovieModel>
                self.discoverLazyPaging = paging
            },
            itemCount: { size in
                self.discoverSize = Int(truncating: size)
            },
            loadStates: {_ in }
        )
        
        wathers.append(watcher)
    }
    
    private func observeSelectedFilters(){
        let watcher = CFlowKt.wrap(state.selectedParams).watch(block: { p in
            self.selectedFilters = p as! [SortFilterItem]
        })
        wathers.append(watcher)
    }
    
    private func observeActions(){
        let watcher = CFlowKt.wrap(state.actions).watch(block: { action in
            let a = action as! any Actions
            if a is ExploreActionsRetry{
                self.discoverLazyPaging?.refresh()
            }
        })
        wathers.append(watcher)
    }
    
    deinit{
        wathers.forEach{ closeble in closeble.close() }
    }

}

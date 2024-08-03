//
//  ViewModelsAssembler.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 02.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Swinject
import Shared

class ViewModelsAssembler: Assembly{
    
    func assemble(container: Container) {
        let helper = UseCaseHelper()
        container.register(HomeViewModel.self, factory: { r in
            HomeViewModel(
                moviesRepository: r.resolve(MoviesListRepository.self)!,
                homeState: helper.homeState()
            )
        })
        container.register(AccountViewModel.self, factory: { r in
            let preferences = r.resolve(AppPreferences.self)!
            return AccountViewModel(appPreferences: preferences)
        }) 
        container.register(FilterViewModel.self, factory: { r in
            let preferences = r.resolve(AppPreferences.self)!
            return FilterViewModel(state: helper.exploreFilterState())
        })
    }
}

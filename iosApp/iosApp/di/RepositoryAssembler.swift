//
//  RepositoryAssembler.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 02.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Swinject
import Shared


class RepositoryAssembler: Assembly{
    
    func assemble(container: Container) {
        let repositoryModule = RepositoryModuleHelper()
        container.register(MoviesListRepository.self, factory: { r in
            repositoryModule.moviesListRepository()
        })
    }
}

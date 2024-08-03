//
//  DatabaseAssembler.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 02.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Swinject
import Shared

class DatabaseAssembler : Assembly{
    
    func assemble(container: Container) {
        let databaseModule = DatabaseModuleHelper()
        container.register(MovieDb.self, factory: { r in
            databaseModule.db()
        })
        container.register(TopRatedMovieDao.self, factory: { r in
            databaseModule.topRatedMovieDao()
        })
        container.register(AppPreferences.self, factory: { r in
            databaseModule.appPreferences()
        })
        container.register(ThemePreferences.self, factory: { r in
            let preferences = r.resolve(AppPreferences.self)!
            return ThemePreferences(appPreferences: preferences)
        })
        container.register(LanguageSetting.self, factory: { r in
            let preferences = r.resolve(AppPreferences.self)!
            return LanguageSetting(appPreferences: preferences)
        })
    }
}

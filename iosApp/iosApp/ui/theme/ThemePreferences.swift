//
//  ThemePreferencec.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 22.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared



class ThemePreferences:ObservableObject{
    
    private let appPreferences:AppPreferences
    
    @Published
    var isDarkMode:Bool? = nil
    
    var watcher:Closeable? = nil
    
    init(appPreferences: AppPreferences) {
        self.appPreferences = appPreferences
        observeIsDarkMode()
    }
    
    private func observeIsDarkMode(){
        self.watcher = CFlowKt.wrap(appPreferences.isDarkMode).watch(block: { [weak self] isDark in
            self?.isDarkMode = isDark as? Bool
        })
    }
    
    deinit{
        watcher?.close()
    }
    
    
}

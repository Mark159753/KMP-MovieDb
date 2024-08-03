//
//  AccountViewModel.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 22.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared


class AccountViewModel: ObservableObject{
    
    private let appPreferences:AppPreferences
    
    @Published
    var isDarkMode:Bool? = nil
    
    @Published
    var appLanguage:AppLanguage = AppLanguage.en
    
    var wathers:[Closeable] = []
    
    init(
        appPreferences:AppPreferences
    ){
        self.appPreferences = appPreferences
        observeIsDarkMode()
        observeAppLanguage()
    }
    
    func onIsDarkModelToggle(isInDark:Bool){
        appPreferences.changeDarkMode(isEnabled: KotlinBoolean(bool: isInDark), completionHandler: { _ in})
    }
    
    func onChangeLanguage(lng:AppLanguage){
        appPreferences.setLocale(lng: lng, completionHandler: { _ in })
    }
    
    private func observeIsDarkMode(){
        let watcher = CFlowKt.wrap(appPreferences.isDarkMode).watch(block: { isDark in
            self.isDarkMode = isDark as? Bool
        })
        wathers.append(watcher)
    }
    
    private func observeAppLanguage(){
        let watcher = CFlowKt.wrap(appPreferences.currentLocale).watch(block: { lng in
            self.appLanguage = lng as! AppLanguage
        })
        wathers.append(watcher)
    }
    
    
    deinit{
        wathers.forEach{ closeble in closeble.close() }
    }
    
}

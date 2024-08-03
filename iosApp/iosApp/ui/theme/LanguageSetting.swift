//
//  LanguageSetting.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 23.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class LanguageSetting: ObservableObject{
    
    private let appPreferences:AppPreferences
    
    @Published
    var locale:Locale = Locale(identifier: NSLocale.current.language.languageCode?.identifier ?? "en")
    @Published
    var languageCode = NSLocale.current.language.languageCode?.identifier ?? "en"
    
    private var watcher:Closeable? = nil
    
    init(appPreferences: AppPreferences) {
        self.appPreferences = appPreferences
        obsorveCurrentLocale()
    }
    
    private func obsorveCurrentLocale(){
        watcher = CFlowKt.wrap(appPreferences.currentLocale).watch(block: { lng in
            let appLng = lng as! AppLanguage
            self.locale = Locale(identifier: appLng.name.lowercased())
            self.languageCode = appLng.name.lowercased()
        })
    }
    
    deinit{
        watcher?.close()
    }
    
}

//
//  View.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 15.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared
import CachedAsyncImage

extension URLCache {
    
    static let imageCache = URLCache(memoryCapacity: 512*1000*1000, diskCapacity: 10*1000*1000*1000)
}

extension Text{
    
    init(res:ResourcesStringResource){
        self.init(
            LocalizedStringKey(res.resourceId),
            bundle: res.bundle
        )
    }
}

extension LanguageSetting{
    
    func getLocalString(res:ResourcesStringResource) -> String{
        return StringResourcesKt.getLocalizedString(res: res, lng: self.languageCode)
    }
}

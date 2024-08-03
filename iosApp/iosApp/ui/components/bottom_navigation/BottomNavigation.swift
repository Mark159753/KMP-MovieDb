//
//  BottomNavigation.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 16.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI


struct BottomNavigation: Identifiable, Equatable, Hashable{
    let title:ResourcesStringResource
    let icon:UIImage
    let rout:Rout
    
    var id: String { title.resourceId }
    
    init(title: ResourcesStringResource, icon: UIImage, rout:Rout) {
        self.title = title
        self.icon = icon
        self.rout = rout
    }
}

let bottomNav = [
    BottomNavigation(
        title: MR.strings.shared.bottom_nav_home,
        icon: MR.images.shared.home.toUIImage()!,
        rout: .home
    ),
    BottomNavigation(
        title: MR.strings.shared.bottom_nav_lists,
        icon: MR.images.shared.list_ic.toUIImage()!,
        rout: .lists
    ),
    BottomNavigation(
        title: MR.strings.shared.bottom_nav_explore,
        icon: MR.images.shared.explore_ic.toUIImage()!,
        rout: .explore
    ),
    BottomNavigation(
        title: MR.strings.shared.bottom_nav_account,
        icon: MR.images.shared.account_circle_ic.toUIImage()!,
        rout: .account
    )
]

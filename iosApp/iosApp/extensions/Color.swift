//
//  Color.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 06.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

extension UIColor{
    
    func toColor() -> Color{
        return Color(self)
    }
}

func colorSchema() -> MovieDbColorSchema{
    return MovieDbColorSchemaKt.movieDbColorSchema
}

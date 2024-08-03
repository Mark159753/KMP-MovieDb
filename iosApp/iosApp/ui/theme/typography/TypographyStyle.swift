//
//  TypographyStyle.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 17.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI


extension Font{
    
    public static func dlsFont(size: CGFloat, weight: Font.Weight =  .regular) -> Font {
        var fontName = "Lora-Regular"
        if weight == .bold {
             fontName = "Lora-Bold"
         }
    return Font.custom(fontName, size: size)
  }
    
    public static func h0(size: CGFloat = 24.0, weight: Font.Weight =  .semibold) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func h1(size: CGFloat = 20.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func h2(size: CGFloat = 16.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func h3(size: CGFloat = 14.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    
    public static func labelRegular(size: CGFloat = 10.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func labelMedium(size: CGFloat = 10.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func labelButton(size: CGFloat = 14.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func tertiaryButton(size: CGFloat = 16.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func linkMedium(size: CGFloat = 14.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func linkSmall(size: CGFloat = 12.0, weight: Font.Weight =  .medium) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func labelText(size: CGFloat = 16.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func supportText(size: CGFloat = 12.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func bodyLarge(size: CGFloat = 16.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func bodyMedium(size: CGFloat = 14.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func bodySmall(size: CGFloat = 12.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    public static func caption(size: CGFloat = 10.0, weight: Font.Weight =  .regular) -> Font{
        return getFontByWeight(size: size, weight: weight)
    }
    
    private static func getFontByWeight(size: CGFloat, weight:Font.Weight) -> Font{
        return switch weight{
        case .light: Font(PoppinsFamily.shared.light(withSize: size))
        case .medium: Font(PoppinsFamily.shared.medium(withSize: size))
        case .semibold: Font(PoppinsFamily.shared.semiBold(withSize: size))
        case .bold: Font(PoppinsFamily.shared.bold(withSize: size))
        default: Font(PoppinsFamily.shared.regular(withSize: size))
        }
    }
}

//
//  AccountScreen.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 15.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

struct AccountScreen: View {
    
    @StateObject private var viewModel = iOSApp.sharedResolver.resolve(AccountViewModel.self)!
    
    @EnvironmentObject var languagesettings: LanguageSetting
    
    @State private var isDarkOn:Bool = false
    
    @Environment(\.colorScheme) var colorScheme
    
    @State private var showLanguageDialog:Bool = false
    
    
    var body: some View {
        VStack{
            Toggle(languagesettings.getLocalString(res: MR.strings.shared.account_screen_use_dark_mode_title),
               isOn: Binding(
                get: {
                    viewModel.isDarkMode ?? (colorScheme == .dark)
                },
                set: { newValue in
                    viewModel.onIsDarkModelToggle(isInDark: newValue)
                }
            ))
            .font(.h2())
            .padding(.horizontal, 16)
            .toggleStyle(SwitchToggleStyle(tint: colorSchema().primary.toColor()))
            
            Text(
                res:MR.strings.shared.account_screen_language_title
            )
            .font(.h2())
            .foregroundColor(colorSchema().onSurface.toColor())
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .frame(maxWidth: .infinity, alignment: .leading)
            .onTapGesture {
                showLanguageDialog = true
            }
            
            Spacer()
        }
        .sheet(isPresented: $showLanguageDialog) {
                    ChangeLanguageDialog(
                        current: Binding(
                            get: {
                                viewModel.appLanguage
                            },
                            set: { newValue in
                                viewModel.onChangeLanguage(lng: newValue)
                                showLanguageDialog = false
                            }
                        )
                    )
                }
        .padding(.horizontal, 16)
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: .infinity)
        .background(colorSchema().background.toColor())
    }
    
}

#Preview {
    AccountScreen()
}

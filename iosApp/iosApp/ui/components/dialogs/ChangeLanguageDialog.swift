//
//  ChangeLanguageDialog.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 22.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ChangeLanguageDialog: View {
    @Binding var current: AppLanguage

    var body: some View {
        VStack {
            Text(res: MR.strings.shared.account_screen_language_title)
                .font(.h1(weight: .semibold))
                .foregroundColor(colorSchema().onSurface.toColor())
                .padding(.top, 16)

            Spacer().frame(height: 24)

            ForEach(AppLanguage.entries, id: \.self) { item in
                LanguageItem(
                    selected: current == item,
                    item: item,
                    onSelect: { newItem in self.current = newItem }
                )
            }
            
            Spacer()
        }
        .background(Color(.systemBackground))
        .padding()
    }
}

struct LanguageItem: View {
    var selected: Bool
    var item: AppLanguage
    var onSelect: (AppLanguage) -> Void

    var body: some View {
        Text(res: item.displayName())
            .font(.bodyMedium())
            .padding(.vertical, 6)
            .frame(maxWidth: .infinity, alignment: .leading)
            .cornerRadius(8)
            .onTapGesture {
                onSelect(item)
            }
            .padding(.horizontal)
            .foregroundColor(selected ? colorSchema().primary.toColor() : colorSchema().onSurface.toColor())
    }
}

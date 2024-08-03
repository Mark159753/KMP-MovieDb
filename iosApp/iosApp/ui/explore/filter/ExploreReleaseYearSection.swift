//
//  ExploreReleaseYearSection.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ExploreReleaseYearSection: View {
    
    @Binding var useReleaseYearFilter:Bool
    @Binding var selectedYear:Double

    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(res:CommonRes.strings.shared.explore_release_year_title)
                    .font(.h2())
                    .foregroundColor(colorSchema().onSurface.toColor())

                Spacer()

                Toggle(isOn: $useReleaseYearFilter) {
                    EmptyView()
                }
                .toggleStyle(SwitchToggleStyle(tint: colorSchema().primary.toColor()))
                .labelsHidden()
            }

            if useReleaseYearFilter {
                VStack {
                    Spacer().frame(height: 10)
                    
                    Text("\(Int(selectedYear))")
                        .font(.bodyMedium())
                        .foregroundColor(colorSchema().onSurface.toColor())

                    Spacer().frame(height: 4)

                    Slider(
                        value: $selectedYear,
                        in: 1930...2024,
                        step: 1
                    )
                    .accentColor(colorSchema().primary.toColor())
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }.animation(.easeIn(duration: 0.2), value: useReleaseYearFilter)
    }
}


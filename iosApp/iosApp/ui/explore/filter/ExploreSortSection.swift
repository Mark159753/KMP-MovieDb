//
//  ExploreSortSection.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ExploreSortSection: View {
    
    var sortBy:SortFilterItem
    let options:[SortFilterItem]
    let onSortBy:(SortFilterItem)->Void

    var body: some View {
        VStack(alignment: .leading) {
            Text(res: CommonRes.strings.shared.explore_sort_by_title)
                .font(.h2())
                .foregroundColor(colorSchema().onSurface.toColor())

            Spacer().frame(height: 16)

            OverflowLayout(spacing: 12){
                ForEach(options, id: \.value) { option in
                    ExploreFilterChip(
                        item: option,
                        selected: option == sortBy,
                        onClick: onSortBy
                    )
                }
            }
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
    }
}

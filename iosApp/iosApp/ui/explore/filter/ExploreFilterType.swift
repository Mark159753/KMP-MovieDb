//
//  ExploreFilterType.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ExploreFilterType: View {
    var selectedItem: ContentType
    let onSelectContentType:(SortFilterItem)->Void
    

    var body: some View {
        VStack(alignment: .leading) {
            Text(res:CommonRes.strings.shared.explore_content_type_title)
                .font(.h2())
                .foregroundColor(colorSchema().onSurface.toColor())

            Spacer().frame(height: 16)

            HStack(spacing: 12) {
                ForEach(ContentType.entries, id: \.self) { type in
                    ExploreFilterChip(
                        item: type.toSortFilterItem(),
                        selected: selectedItem == type,
                        onClick: onSelectContentType
                    )
                }
            }
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
    }
}

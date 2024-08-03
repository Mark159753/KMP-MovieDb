//
//  ExploreGenresFilter.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ExploreGenresFilter: View {
    
    var genres:[SortFilterItem]
    var selectedGenres:Set<SortFilterItem>
    
    let onSelectGenre:(SortFilterItem)->Void
    

    var body: some View {
        VStack(alignment: .leading) {
            Text(res:CommonRes.strings.shared.explore_genre_title)
                .font(.h2())
                .foregroundColor(colorSchema().onSurface.toColor())

            Spacer().frame(height: 16)
            
            OverflowLayout(spacing: 12){
                ForEach(genres, id: \.value) { genre in
                    ExploreFilterChip(
                        item: genre,
                        selected: selectedGenres.contains(genre),
                        onClick: onSelectGenre
                    )
                }
            }
        }
    }
}

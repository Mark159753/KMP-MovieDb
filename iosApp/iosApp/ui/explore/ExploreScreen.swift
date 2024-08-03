//
//  ExploreScreen.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 15.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ExploreScreen: View {
    
    @Binding var path:NavigationPath
    
    @StateObject private var viewModel = iOSApp.sharedResolver.resolve(FilterViewModel.self)!
    
    var body: some View {
        VStack {
            HeaderItem(onFilterClick: {
                path.append(Rout.exploreFilter)
            })
                .padding(.horizontal, 16)

            Spacer()
                .frame(height: 10)
            
            ScrollView(.horizontal, showsIndicators: false){
                LazyHStack(spacing: 12){
                    ForEach(viewModel.selectedFilters, id: \.self) { item in
                        ExploreFilterChip(item: item, selected: false)
                    }
                }
                .padding(.horizontal, 16)
            }
            .frame(height: 30)
            
            ScrollView(.vertical, showsIndicators: false){
                LazyVStack(alignment: .leading, spacing: 12){
                    ForEach(0..<viewModel.discoverSize, id: \.self) { index in
                        let item = viewModel.discoverLazyPaging?.get(index: Int32(index))
                        ExploreMovieItem(item: item)
                    }
                }
                .padding(.horizontal, 16)
            }
        
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(.systemBackground))
    }
}

struct HeaderItem: View {
    var onFilterClick: () -> Void = {}

    var body: some View {
        HStack {
            Text(res:MR.strings.shared.explore_title)
                .font(.h0())
                .foregroundColor(colorSchema().onSurface.toColor())

            Spacer()

            Button(action: onFilterClick) {
                Image(uiImage: MR.images.shared.filter_alt.toUIImage()!)
                    .renderingMode(.template)
                    .foregroundColor(colorSchema().onSurface.toColor())
            }
        }
    }
}


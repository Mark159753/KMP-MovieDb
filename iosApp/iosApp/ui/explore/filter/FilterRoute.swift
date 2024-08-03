//
//  FilterRoute.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct FilterRoute: View {
    
    @Binding var path:NavigationPath
    
    @StateObject private var viewModel = iOSApp.sharedResolver.resolve(FilterViewModel.self)!
    
    var body: some View {
        VStack(alignment:.leading) {
          TopAppBar(onNavBack: { path.removeLast() })
          
          LazyVStack(spacing: 0) {
              Spacer().frame(height: 16)
              
              ExploreFilterType(
                selectedItem: viewModel.contentType, onSelectContentType: viewModel.state.contentTypeState.onSelectContentType
              )
              
              Spacer().frame(height: 16)
              
              ExploreSortSection(sortBy: viewModel.sortBy, options: viewModel.state.sortState.sortOptions, onSortBy: viewModel.state.sortState.onSortBy)
              
              Spacer().frame(height: 16)
              
              ExploreReleaseYearSection(
                useReleaseYearFilter: Binding(
                    get: {
                        viewModel.useReleaseYearFilter
                    },
                    set: { newValue in
                        viewModel.state.releaseYearStateFilter.onUseReleaseYearFilter(use: newValue)
                    }
                ),
                selectedYear: Binding(
                    get: {
                        Double(viewModel.selectedYear)
                    },
                    set: { newValue in
                        viewModel.state.releaseYearStateFilter.onReleaseYearChanged(year: Int32(Int(newValue)))
                    }
                )
              )
              
              Spacer().frame(height: 16)
              
              ExploreGenresFilter(
                genres: viewModel.genres,
                selectedGenres: viewModel.selectedGenres,
                onSelectGenre: viewModel.state.genreState.onSelectGenre
              )
          }
          .padding(.horizontal, 16)
            
            Spacer()
            
            HStack {
                Spacer()

                Button(action: {
                    viewModel.state.onClear()
                    path.removeLast()
                }) {
                    Text(res: MR.strings.shared.explore_filer_clear_btn_title)
                }
                .buttonStyle(MovieDbButtonStyleVariant())
                .frame(maxWidth: 200)
                .padding(.horizontal, 16)

                Spacer().frame(width: 8)

                Button(action: {
                    viewModel.state.onApply()
                    path.removeLast()
                }) {
                    Text(res: MR.strings.shared.explore_filer_apply_btn_title)
                }
                .buttonStyle(MovieDbButtonStyle())
                .frame(maxWidth: 200)

                Spacer()
            }
            .padding(.top, 4)
            .padding(.bottom, 16)
            .padding(.horizontal, 16)
      }
        .background(colorSchema().background.toColor())
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

struct TopAppBar: View {
    var onNavBack: () -> Void

    var body: some View {
        HStack {
            Button(action: onNavBack) {
                Image(systemName: "arrow.left")
                    .resizable()
                    .frame(width: 18, height: 18)
            }
            .padding()

            Spacer()
            
            Text(res:MR.strings.shared.explore_filer_title)
                .font(.h1())
                .foregroundColor(colorSchema().onPrimaryContainerVariant.toColor())
                .frame(maxWidth: .infinity, alignment: .center)
            
            Spacer()
        }
        .background(colorSchema().surface.toColor())
        .foregroundColor(colorSchema().onPrimaryContainerVariant.toColor())
    }
}

struct FilterRoute_Previews: PreviewProvider {
    @State static var path = NavigationPath()

    static var previews: some View {
        FilterRoute(path: $path)
    }
}

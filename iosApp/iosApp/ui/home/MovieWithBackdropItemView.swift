//
//  MovieWithBackdropItemView.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 18.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import CachedAsyncImage

struct MovieWithBackdropItemView: View {
    
    var item: MovieModel?
    
    var body: some View {
          VStack {
              if let backdropPath = item?.backdropPath {
                  CachedAsyncImage(
                    url: URL(string: ConstantsKt.toBackdropPath(backdropPath,size:.w780)),
                    urlCache: .imageCache
                  ) { image in
                      image
                          .resizable()
                          .scaledToFill()
                          .frame(width: 248, height: 116)
                          .clipShape(RoundedRectangle(cornerRadius: 6))
                  } placeholder: {
                      colorSchema().shimmer.toColor()
                          .frame(width: 248, height: 116)
                          .clipShape(RoundedRectangle(cornerRadius: 6))
                  }
                  
                  Spacer()
                      .frame(height: 4)
              }

              MovieWithDetailsItem(item: item)
          }
      }
}

struct MovieWithDetailsItem: View {
    var item: MovieModel?

    var body: some View {
        HStack(alignment: .top) {
            if let posterPath = item?.posterPath {
                CachedAsyncImage(
                    url: URL(string: ConstantsKt.toPosterPath(posterPath, size:.w780)),
                    urlCache: .imageCache
                ) { image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 106, height: 152)
                        .clipShape(RoundedRectangle(cornerRadius: 6))
                } placeholder: {
                    colorSchema().shimmer.toColor()
                        .frame(width: 106, height: 152)
                        .clipShape(RoundedRectangle(cornerRadius: 6))
                }
            }

            Spacer()
                .frame(width: 4)

            VStack(alignment: .leading) {
                Text(item?.title ?? "")
                    .font(.h3())
                    .foregroundColor(colorSchema().onSurface.toColor())
                    .lineLimit(2)
                    .truncationMode(.tail)

                Spacer()
                    .frame(height: 4)

                HStack {
                    Image(uiImage: MR.images.shared.star_rate_ic.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .frame(width: 16, height: 16)
                        .foregroundColor(.yellow)

                    Spacer()
                        .frame(width: 4)

                    Text(String(format: "%.1f", item?.voteAverage ?? 0.0))
                        .foregroundColor(colorSchema().onSurface.toColor())
                        .font(.bodySmall())

                    if let releaseDate = item?.releaseDate {
                        Spacer()
                            .frame(width: 8)
                        Text(releaseDate)
                            .foregroundColor(colorSchema().onSurface.toColor())
                            .font(.bodySmall())
                    }
                }

                if let overview = item?.overview {
                    Spacer()
                        .frame(height: 6)
                    Text(overview)
                        .foregroundColor(colorSchema().onSurface.toColor())
                        .font(.bodySmall())
                        .lineLimit(5)
                        .truncationMode(.tail)
                }
            }
            .frame(width: 134)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        let dymmyItem = MovieModel(id: 1,
                                   adult: false,
                                   backdropPath: "https://image.tmdb.org/t/p/w500/backdrop.jpg",
                                   genreIds: [28, 12, 16],
                                   originalLanguage: "en",
                                   originalTitle: "Original Title",
                                   overview: "This is a sample overview of the movie. It provides a brief description of the plot and main elements.",
                                   popularity: 123.45,
                                   posterPath: "https://image.tmdb.org/t/p/w500/poster.jpg",
                                   releaseDate: "2024-07-18",
                                   title: "Movie Title",
                                   video: false,
                                   voteAverage: 8.5,
                                   voteCount: 1234)
        
        MovieWithBackdropItemView(item: dymmyItem)
    }
}

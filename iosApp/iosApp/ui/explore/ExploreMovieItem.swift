//
//  ExploreMovieItem.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 29.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

import SwiftUI

struct ExploreMovieItem: View {
    var item: MovieModel?

    var body: some View {
        HStack(alignment: .center) {
            AsyncImage(
                url: URL(
                    string:ConstantsKt.toPosterPath(item?.posterPath ?? "", size:.w780)
                ),
                content: { image in
                    image
                        .resizable()
                        .scaledToFill()
                },
                placeholder: {
                    Rectangle()
                        .foregroundColor(colorSchema().shimmer.toColor())
                }
            )
            .frame(width: 84, height: 128)
            .background(colorSchema().shimmer.toColor())

            Spacer().frame(width: 8)

            VStack(alignment: .leading) {
                Text(item?.title ?? "")
                    .font(.h2())
                    .foregroundColor(colorSchema().onSurface.toColor())
                    .lineLimit(1)
                    .truncationMode(.tail)

                Spacer().frame(height: 4)

                Text(item?.releaseDate ?? "")
                    .font(.bodySmall())
                    .foregroundColor(colorSchema().onSurface.toColor())
                    .lineLimit(1)
                    .truncationMode(.tail)

                Spacer().frame(height: 8)

                Text(item?.overview ?? "")
                    .font(.bodySmall())
                    .foregroundColor(colorSchema().onSurface.toColor())
                    .lineLimit(4)
                    .truncationMode(.tail)
                    .frame(maxHeight: .infinity)
            }
        }
        .frame(height: 128)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}


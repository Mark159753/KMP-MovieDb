//
//  PersonItemView.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 19.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import CachedAsyncImage

struct PersonItemView: View {
    var item: PersonModel?

    var body: some View {
        VStack {
            if let profilePath = item?.profilePath {
                CachedAsyncImage(
                    url: URL(string: ConstantsKt.toProfilePath(profilePath,size:.w185)),
                    urlCache: .imageCache
                ) { image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 80, height: 80)
                        .clipShape(Circle())
                } placeholder: {
                    colorSchema().shimmer.toColor()
                        .frame(width: 80, height: 80)
                        .clipShape(Circle())
                }
            } else {
                Image(systemName: "person.fill")
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width: 80, height: 80)
                    .background(Color.gray)
                    .clipShape(Circle())
            }

            Spacer().frame(height: 6)

            Text(item?.name ?? "")
                .font(.h3())
                .foregroundColor(colorSchema().onSurface.toColor())
                .lineLimit(2)
                .multilineTextAlignment(.center)
                .frame(maxWidth: .infinity)
        }
        .frame(width: 90, height: 130, alignment: .top)
    }
}

#Preview {
    PersonItemView()
}

struct PersonItemView_Previews: PreviewProvider {
    static var previews: some View {
        let dymmyItem = PersonModel(
            id: 1,
            adult: false,
            gender: 2,
            knownFor: [],
            knownForDepartment: "Acting",
            mediaType: "person",
            name: "John Doe",
            originalName: "John Doe",
            popularity: 10.0,
            profilePath: "https://example.com/path/to/profile.jpg",
            pagingOrder: 1.0
        )
        PersonItemView(item: dymmyItem)
    }
}

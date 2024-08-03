//
//  HomePagerView.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 17.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import CachedAsyncImage

struct HomePagerView: View {
    
    var trendsList:[TrendingModel]
    
    @State var currentIndex:Int = 0
    
    let gradientHeight:CGFloat
    
    var body: some View {
        VStack{
            TabView(selection: $currentIndex.animation()){
                ForEach(trendsList.indices, id: \.self){ index in
                    let item = trendsList[index]
                    TrendingItemView(item: item, gradientHeight: self.gradientHeight)
                    .tag(index)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .indexViewStyle(.page(backgroundDisplayMode: .never))
            
            Spacer().frame(height: 16)
            
            Indicators(numberOfPages: trendsList.count, currentIndex: self.currentIndex)
        }
    }
}

struct TrendingItemView:View {
    
    let item:TrendingModel
    let gradientHeight:CGFloat
    
    @EnvironmentObject var languagesettings: LanguageSetting
    
    var body: some View {
        
        ZStack(alignment:.bottom){
            GeometryReader{ geometry in
                CachedAsyncImage(
                    url: URL(
                        string:ConstantsKt.toPosterPath(item.posterPath ?? "", size:.w780)
                    ),
                    urlCache: .imageCache
                ) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: geometry.size.width)
                        .clipped()
                } placeholder: {
                    colorSchema().shimmer.toColor()
                }
                .frame(width: geometry.size.width)
                
            }
            
            LinearGradient(gradient: Gradient(colors: [colorSchema().background.toColor().opacity(0), colorSchema().background.toColor()]), startPoint: .top, endPoint: .bottom)
                .frame(height: gradientHeight)
            
            
            VStack{
                Text(
                    item.title ?? item.name ?? ""
                )
                .lineLimit(2)
                .font(.h2(weight: .semibold))
                .foregroundColor(colorSchema().onSurface.toColor())
                
                
                Spacer().frame(height: 8)
                
                Details(item: item)
                
                Spacer().frame(height: 12)
                
                HStack(spacing: 16){
                    
                    Button(
                        languagesettings.getLocalString(res: MR.strings.shared.home_trends_slider_details)
                    ) {
                                print("Button pressed!")
                            }
                            .buttonStyle(MovieDbButtonStyleVariant())
                    
                    Button(
                        languagesettings.getLocalString(res: MR.strings.shared.home_trends_slider_add_to_list)
                    ) {
                                print("Button pressed!")
                            }
                            .buttonStyle(MovieDbButtonStyle())
                }
                
                
            }
            .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
            .padding(.horizontal, 16)
        }
    }
}

struct Details:View {
    
    let item:TrendingModel
    
    var body: some View {
        HStack(alignment: .center, spacing: 16) {
            HStack(alignment: .center) {
                Image(
                    uiImage: MR.images.shared.star_rate_ic.toUIImage()!
                )
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.black)
                .frame(width: 16, height: 16)
        
                Spacer().frame(width: 4)
                
                Text(
                    String(format: "%.1f", item.voteAverage)
                )
                .font(.bodySmall())
                .foregroundColor(.black)
           }
           .padding(.horizontal, 8)
           .padding(.vertical, 3)
           .background(Color.white)
           .clipShape(Capsule())
            
            if let releaseDate = item.releaseDate ?? item.firstAirDate {
               let year = String(releaseDate.prefix(4))
               Text(year)
                    .foregroundColor(colorSchema().onSurface.toColor())
                   .font(.bodySmall())
           }
        }
        .frame(maxWidth: .infinity, alignment: .center)
    }
}

struct Indicators: View {
    let numberOfPages: Int
    let currentIndex: Int

    private let circleSize: CGFloat = 10
    private let circleSpacing: CGFloat = 6

    private let primaryColor = colorSchema().primary.toColor()
    private let secondaryColor = colorSchema().onPrimaryContainerVariant.toColor()

    private let smallScale: CGFloat = 0.8

    var body: some View {
        HStack(spacing: circleSpacing) {
            ForEach(0..<numberOfPages, id: \.self) { index in
                Circle()
                    .fill(currentIndex == index ? primaryColor : secondaryColor)
                    .scaleEffect(currentIndex == index ? 1 : smallScale)
                    .frame(width: circleSize, height: circleSize)
                    .transition(AnyTransition.opacity.combined(with: .scale))
                    .id(index)
            }
        }
    }
}

struct HomePagerView_Previews: PreviewProvider {
    static var previews: some View {
       
        let list = [
            TrendingModel(id: 1, adult: false, backdropPath: nil, firstAirDate: nil, genreIds: [], mediaType: .movie, name: "Hello", originCountry: ["us"], originalLanguage: "uk", originalName: "Hello", originalTitle: "Hello", overview: "Hello", popularity: 1.0, posterPath: "", releaseDate: "", title: "Hello", video: false, voteAverage: 12, voteCount: 12),
            TrendingModel(id: 2, adult: false, backdropPath: nil, firstAirDate: nil, genreIds: [], mediaType: .movie, name: "Hello", originCountry: ["us"], originalLanguage: "uk", originalName: "Hello", originalTitle: "Hello", overview: "Hello", popularity: 1.0, posterPath: "", releaseDate: "", title: "Hello", video: false, voteAverage: 12, voteCount: 12),
        ]

        return HomePagerView(trendsList: list, gradientHeight: 150)
    }
}

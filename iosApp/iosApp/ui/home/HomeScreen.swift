//
//  HomeScreen.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 02.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct HomeScreen: View {
    
    @StateObject private var homeViewModel = iOSApp.sharedResolver.resolve(HomeViewModel.self)!
    
    var body: some View {
        GeometryReader(content: { geometry in
            ScrollView{
                HomePagerView(
                    trendsList: homeViewModel.trendsList,
                    gradientHeight: (geometry.size.height * 0.5) * 0.4
                )
                .frame(
                    width: geometry.size.width,
                    height: geometry.size.height * 0.5
                )
                
                VStack(alignment: .leading, content: {
                    //Upcoming
                    sectionTitleView(
                        res: MR.strings.shared.home_screen_upcoming_title
                    ).frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                
                    
                    ScrollView(.horizontal, showsIndicators: false){
                        LazyHStack(spacing: 16){
                            ForEach(0..<homeViewModel.upcomingMoviesSize, id: \.self) { index in
                                let item = homeViewModel.upcomingLazyPaging?.get(index: Int32(index))
                                MovieWithBackdropItemView(item: item)
                            }
                        }
                        .padding(.horizontal, 16)
                    }
                    
                    //TV trends
                    sectionTitleView(
                        res: MR.strings.shared.home_screen_tv_trends_title
                    ).frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                
                    
                    ScrollView(.horizontal, showsIndicators: false){
                        LazyHStack(spacing: 16){
                            ForEach(0..<homeViewModel.tvTrendsSize, id: \.self) { index in
                                let item = homeViewModel.tvTrendsLazyPaging?.get(index: Int32(index))
                                MovieWithDetailsItem(item: item)
                            }
                        }
                        .padding(.horizontal, 16)
                    }
                    
                    //Trending People
                    sectionTitleView(
                        res: MR.strings.shared.home_screen_people_title
                    ).frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                
                    
                    ScrollView(.horizontal, showsIndicators: false){
                        LazyHStack(alignment: .top, spacing: 16){
                            ForEach(0..<homeViewModel.trendingPeopleSize, id: \.self) { index in
                                let item = homeViewModel.trendingPeopleLazyPaging?.get(index: Int32(index))
                                PersonItemView(item: item)
                            }
                        }
                        .padding(.horizontal, 16)
                    }
                })
                .padding(.bottom, 16)
            
            }
            .edgesIgnoringSafeArea(.all)
        })
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: .infinity)
        .background(colorSchema().background.toColor())
    
    }
    
    @ViewBuilder
     func sectionTitleView(res: ResourcesStringResource) -> some View {
         VStack(alignment: .leading, content: {
             Spacer().frame(height: 32)
             
             Text(res: res)
                 .font(.h2())
                 .foregroundColor(colorSchema().onSurface.toColor())
                 .padding(.horizontal, 16)
                 .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
             
             Spacer().frame(height: 16)
         })
     }
}

#Preview {
    HomeScreen()
}

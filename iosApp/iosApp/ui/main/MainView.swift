//
//  MainView.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 15.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct MainView: View {
    
    @State var selected:BottomNavigation = bottomNav[0]
    @State var path = NavigationPath()
    
    init() {
        UITabBar.appearance().isHidden = true
    }
    
    var body: some View {
        VStack{
            NavigationStack(path: $path){
                TabView(
                    selection: $selected,
                    content:  {
                        HomeScreen()
                            .tag(bottomNav[0])
                        ListsScreen()
                            .tag(bottomNav[1])
                        ExploreScreen(path: $path)
                            .tag(bottomNav[2])
                        AccountScreen()
                            .tag(bottomNav[3])
                    }
                )
                .navigationDestination(for: Rout.self, destination: { route in
                    switch route {
                    case .exploreFilter:
                           FilterRoute(path: $path)
                            .navigationBarBackButtonHidden(true)
                    default: Text("Wrong destination")
                    }
                })
            }
            
            ZStack{
                if path.isEmpty {
                    MovieDbBottomNavBar(
                        selected: self.$selected
                    )
                    .padding(.vertical)
                    .safeAreaPadding(.bottom)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                    .background(colorSchema().surface.toColor())
//                    .clipped()
//                    .shadow(radius: 4)
                    .transition(.move(edge: .bottom).combined(with: .opacity))
                }
            }.animation(.easeInOut(duration: 0.2), value: path.isEmpty)
        }
        .ignoresSafeArea(.all)
    }
}

#Preview {
    MainView()
}

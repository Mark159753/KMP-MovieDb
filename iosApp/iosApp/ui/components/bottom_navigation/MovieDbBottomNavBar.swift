//
//  MovieDbBottomNavBar.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 16.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct MovieDbBottomNavBar: View {
    
    @Binding var selected:BottomNavigation
    
    var body: some View {
        HStack{
            ForEach(bottomNav, content: { destination in
                Spacer()
                ItemNavigation(
                    item: destination,
                    selected: $selected
                )
            })
            Spacer()
        }
    }
}


struct ItemNavigation:View {
    
    let item:BottomNavigation
    @Binding var selected:BottomNavigation
    
    let colors = DbBottomNavBarColors()
    
    var body: some View {
        HStack{
            Image(uiImage: item.icon)
                .resizable()
                .renderingMode(.template)
                .foregroundColor($selected.wrappedValue == item ? colors.selectedLabel : colors.label)
                .frame(width: 24, height: 24)
                    
            
            if ($selected.wrappedValue == item){
                Spacer().frame(width: 4)
                
                Text(
                    LocalizedStringKey(item.title.resourceId),
                    bundle: item.title.bundle
                )
                .font(.bodySmall(weight: .semibold))
                .foregroundColor(colors.selectedLabel)
            }
        }
        .onTapGesture {
            withAnimation(.easeIn(duration: 0.18)){
                self.selected = self.item
            }
        }
        .padding(.horizontal, 12)
        .padding(.vertical, 6)
        .background($selected.wrappedValue == item ? colors.selectedItemBg : colors.container)
        .clipShape(.capsule)
    }
}

struct DbBottomNavBarColors{
    let container:Color = MovieDbColorSchemaKt.movieDbColorSchema.surface.toColor()
    let selectedItemBg:Color = MovieDbColorSchemaKt.movieDbColorSchema.primary.toColor()
    let selectedLabel:Color = MovieDbColorSchemaKt.movieDbColorSchema.onPrimaryContainer.toColor()
    let label:Color = MovieDbColorSchemaKt.movieDbColorSchema.onPrimaryContainerVariant.toColor()
}

struct MovieDbBottomNavBar_Previews: PreviewProvider {
    @State static var selected: BottomNavigation = bottomNav[0]
    
    static var previews: some View {
        MovieDbBottomNavBar(selected: $selected)
    }
}

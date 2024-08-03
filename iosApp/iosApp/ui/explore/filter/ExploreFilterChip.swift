//
//  ExploreFilterChip.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 27.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct ExploreFilterChip: View {
    var item: SortFilterItem
    var selected: Bool
    var onClick: ((SortFilterItem) -> Void)? = nil
    
    var body: some View {
        HStack {
            if selected {
                HStack {
                    Image(systemName: "checkmark")
                        .resizable()
                        .frame(width: 12, height: 12)
                        .foregroundColor(selected ? Color.black : colorSchema().unselectedChipBorder.toColor())
                    Spacer().frame(width: 4)
                }
                .transition(.opacity)
            }
            
            if item.displayName != nil{
                Text(item.displayName!)
                    .foregroundColor(selected ? Color.black : colorSchema().unselectedChipBorder.toColor())
                    .font(.h3())
            }else{
                Text(res:item.displayNameResource!)
                    .foregroundColor(selected ? Color.black : colorSchema().unselectedChipBorder.toColor())
                    .font(.system(size: 16, weight: .medium))
            }
            
            
        }
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .background(selected ? colorSchema().selectedChipBg.toColor() : Color.clear)
        .cornerRadius(100)
        .overlay(
            RoundedRectangle(cornerRadius: 100)
                .stroke(selected ? colorSchema().selectedChipBorder.toColor() : colorSchema().unselectedChipBorder.toColor(), lineWidth: 1)
        )
        .animation(/*@START_MENU_TOKEN@*/.easeIn/*@END_MENU_TOKEN@*/(duration: 0.2), value: selected)
        .onTapGesture {
            if let onClick = onClick {
                onClick(item)
            }
        }
    }
}

struct ExploreFilterChip_Previews: PreviewProvider {
    
    @State static var selected = 0
    
    static var previews: some View {
        VStack {
            ExploreFilterChip(
                item: SortFilterItem(displayNameResource: nil, displayName: "Example Item", value: ""),
                selected: selected == 0,
                onClick: { _ in selected = 0;  print("CLICK \(selected)") }
            )
            ExploreFilterChip(
                item: SortFilterItem(displayNameResource: nil, displayName: "Example Item", value: ""),
                selected: selected == 1,
                onClick: { _ in selected = 1; print("CLICK \(selected)") }
            )
        }
        .padding()
    }
}

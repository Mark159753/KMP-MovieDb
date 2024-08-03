//
//  MovieDbButtonStyle.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 18.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI


struct MovieDbButtonStyle: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.bodyMedium())
            .lineLimit(1)
            .padding(.vertical, 6)
            .padding(.horizontal, 12)
            .background(colorSchema().primary.toColor())
            .foregroundStyle(.black)
            .clipShape(RoundedRectangle(cornerSize: CGSize(width: 6, height: 6)))
    }
}

struct MovieDbButtonStyleVariant: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.bodyMedium())
            .lineLimit(1)
            .padding(.vertical, 6)
            .padding(.horizontal, 12)
            .background(colorSchema().secondary.toColor())
            .foregroundStyle(.white)
            .clipShape(RoundedRectangle(cornerSize: CGSize(width: 6, height: 6)))
    }
}

struct MovieDbButtonStylePreview: PreviewProvider{
    
    static var previews: some View{
        VStack(spacing: 12){
            Button("Press Me") {
                        print("Button pressed!")
                    }
                    .buttonStyle(MovieDbButtonStyle())
            
            Button("Press Me") {
                        print("Button pressed!")
                    }
                    .buttonStyle(MovieDbButtonStyleVariant())
        }
    }
}

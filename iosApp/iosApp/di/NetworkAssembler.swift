//
//  NetworkAssembler.swift
//  iosApp
//
//  Created by Mark Melnarowycz on 02.07.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Swinject
import Shared

class NerworkAssembler : Assembly{
    
    func assemble(container:Container){
        let networkModule = NetworkModuleHelper()
        container.register(Ktor_client_coreHttpClient.self, factory: { r in
            networkModule.httpClient()
        })
        container.register(ApiService.self, factory: { r in
            networkModule.apiService()
        })
    }
    
}

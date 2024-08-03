import SwiftUI
import Shared
import Swinject

@main
struct iOSApp: App {
    
    static let sharedResolver = Assembler([
        NerworkAssembler(),
        DatabaseAssembler(),
        RepositoryAssembler(),
        ViewModelsAssembler(),
    ], container: Container()).resolver
    
    @StateObject var themePref:ThemePreferences = iOSApp.sharedResolver.resolve(ThemePreferences.self)!
    @StateObject var languageSetting:LanguageSetting = iOSApp.sharedResolver.resolve(LanguageSetting.self)!
    @Environment(\.colorScheme) var colorScheme

    
    init(){
        HelperKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            MainView()
                .preferredColorScheme(
                    themePref.isDarkMode ?? (colorScheme == .dark) ? .dark : .light
                )
                .environmentObject(languageSetting)
                .environment(\.locale, languageSetting.locale)
        }
    }
}

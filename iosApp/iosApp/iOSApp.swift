import SwiftUI
import Firebase
import shared

@main
struct iOSApp: App {
    
    init() {
        FirebaseApp.configure()
        KoinHelperKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

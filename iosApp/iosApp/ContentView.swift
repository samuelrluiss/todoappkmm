import SwiftUI
import shared

struct ContentView: View {    
    var title = Strings().get(id: SharedResources.strings().notes, args: [])
    
    var body: some View {
        NavigationStack  {
                HomeScreen()
            .toolbar {
                ToolbarItemGroup(placement: .navigationBarTrailing) {
                    NavigationLink(destination: {
                        CreateNoteScreen()
                    }, label: {
                        Image(systemName: "plus")
                    })
                }

                ToolbarItem(placement: .principal) {
                    HStack {
                        Image(resource: \.note)
                            .resizable()
                            .frame(width: 30, height: 30)
                        Text(title)
                    }
                }
            }
        }
    }
}

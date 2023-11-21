import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private var observableState = ObservableState()
    
    var title = Strings().get(id: SharedResources.strings().notes, args: [])
    
    var body: some View {
        NavigationView {
         
            
            /*EmptyView()
                .toolbar {
                ToolbarItem(placement: .principal) {
                    HStack {
                        Image(systemName: "note.text")
                            .resizable()
                            .frame(width: 30, height: 30)
                        Text(title)
                    }
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button(action: {
                        // Handle the "Add" button tap
                        // You can perform some action here, for example, navigate to a new screen
                    }) {
                        Image(systemName: "plus")
                    }
                }
            }*/
            
            if !observableState.notes.isEmpty {
                List(observableState.notes, id: \.title) { note in
                    VStack(alignment: .leading) {
                        Text(note.title)
                            .font(.headline)
                        Text(note.text)
                            .font(.subheadline)
                    }
                }
            } else {
                Text("Loading...")
            }
            

        }
    }
}

class ObservableState: ObservableObject {
    @Published var viewModel: HomeScreenViewModel = KoinHelper().getHomeScreenViewModel()
    @Published var notes: [Note] = []

    init() {
        self.viewModel.listOfNotes.addObserver { (value) in
            self.notes = value as? [Note] ?? []
        }
    }
}


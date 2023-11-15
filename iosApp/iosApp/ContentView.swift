import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = FirebaseViewModel()
    var title = Strings().get(id: SharedResources.strings().notes, args: [])
    
    var body: some View {
        NavigationView {
            List(viewModel.notes, id: \.title) { note in
                VStack(alignment: .leading) {
                    Text(note.title)
                        .font(.headline)
                    Text(note.text)
                        .font(.subheadline)
                }
            }
            .navigationTitle(title)
            .onAppear {
                viewModel.loadNotes()
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

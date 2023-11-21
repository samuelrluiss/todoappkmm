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
            .toolbar {
                ToolbarItem(placement: .principal) {
                    HStack {
                        Image(resource: \.note)
                            .resizable()
                            .frame(width: 30, height: 30) // Adjust the size as needed
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
            }
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

import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = FirebaseViewModel()
    var title = Strings().get(id: SharedResources.strings().notes, args: [])
    
    var body: some View {
        NavigationView {
            List(viewModel.notes, id: \.title) { note in
                HStack {
                    Image(resource: \.note)
                
                VStack(alignment: .leading) {
                    Text(note.title)
                        .font(.headline)
                    Text(note.text)
                        .font(.subheadline)
                }
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

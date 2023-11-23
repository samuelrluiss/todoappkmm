import com.example.todoappkmm.getPlatform
import com.example.todoappkmm.model.Note
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.serialization.Serializable

class FirebaseRepository {
    private val firebaseFirestore = Firebase.firestore
    suspend fun getNotes(): List<Note> {

        try {
            val notesResponse =
                firebaseFirestore.collection("NOTES").get()
            return notesResponse.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun createNote(title: String, text: String) {
        try {
            firebaseFirestore.collection("NOTES").add(Note(title = title, text = text, platform = getPlatform().name))
        } catch (e: Exception) {
            throw e
        }
    }
}
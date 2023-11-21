import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val title: String,
    val text: String
)

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

    suspend fun createNote() {
        try {
            firebaseFirestore.collection("NOTES").add(Note("Test", "TestText"))
        } catch (e: Exception) {
            throw e
        }
    }
}
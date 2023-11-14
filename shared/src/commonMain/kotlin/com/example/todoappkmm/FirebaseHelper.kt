import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val title: String,
    val text: String
)
class FirebaseHelper {
    suspend fun getNotes(): List<Note> {
        val firebaseFirestore = Firebase.firestore
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
}
package usecases

import FirebaseRepository
import com.example.todoappkmm.model.Note
import com.example.todoappkmm.utils.Dispatcher
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateNoteUseCase : KoinComponent {
    private val firebaseRepository: FirebaseRepository by inject()
    private val dispatcher: Dispatcher by inject()

    suspend fun execute(title : String, text : String) = withContext(dispatcher.io) {
        firebaseRepository.createNote(title, text)
    }
}
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.concurrent.thread

object AppState {
    var uiState: UiState by mutableStateOf(UiState())

    fun loadNotes() {
        thread {
            uiState = uiState.copy(loading = true)
            getNotes() { notes ->
                uiState = uiState.copy(notes = notes, loading = false)
            }
        }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean = false
    )
}
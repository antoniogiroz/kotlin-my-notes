import androidx.compose.runtime.mutableStateOf
import kotlin.concurrent.thread

class AppState {
    var uiState = mutableStateOf(UiState())

    fun loadNotes() {
        thread {
            uiState.update { state -> state.copy(loading = true) }
            getNotes() { notes ->
                uiState.update { state -> state.copy(notes = notes, loading = false) }
            }
        }
    }

    data class UiState(
        val notes: List<Note>? = null,
        val loading: Boolean = false
    )
}
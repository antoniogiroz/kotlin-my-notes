import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App(appState: AppState) {
    val (notes, loading) = appState.uiState.value

    if (notes == null) {
        LaunchedEffect(true) { appState.loadNotes() }
    }


    MaterialTheme {
        Box(contentAlignment = Alignment.Center) {
            if (loading) {
                CircularProgressIndicator()
            }
            NotesList(notes ?: emptyList())
        }
    }
}


fun main() {
    val appState = AppState()

    application {
        Window(onCloseRequest = ::exitApplication, title = "My Notes") {
            App(appState)
        }
    }
}

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App(): Unit = with(AppState) {
    val (notes, loading) = uiState.value

    if (notes == null) {
        LaunchedEffect(true) { loadNotes() }
    }

    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (loading) {
                CircularProgressIndicator()
            }
            notes?.let { NotesList(notes) }
        }
    }
}


fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "My Notes") {
            App()
        }
    }
}

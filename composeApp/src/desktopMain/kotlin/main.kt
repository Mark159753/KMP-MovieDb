import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.initKoin
import ui.MovieDBApp

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "MovieDB_KMP",
    ) {
        MovieDBApp()
    }
}
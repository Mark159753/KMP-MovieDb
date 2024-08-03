
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.moviedb.kmp.common.preferences.AppPreferences
import org.moviedb.kmp.data.repositories.movies.list.MoviesListRepository
import org.moviedb.kmp.ui.theme.MovieDbTheme
import org.moviedb.kmp.ui.theme.localColors

@Composable
@Preview
fun App(
    repository: MoviesListRepository = koinInject(),
    themePreferences: AppPreferences = koinInject()
) {

    val list by repository.topRatedMovies.collectAsState(emptyList())
    val isDark by themePreferences.isDarkMode.collectAsState(null)

    LaunchedEffect(Unit){
        repository.fetchTopRatedMovies(1)
    }

    MovieDbTheme(
        isDarkTheme = isDark ?: isSystemInDarkTheme()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.localColors.background)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(list) { item ->
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(200.dp)
                            .background(Color.Cyan)
                    ) {
                        Text(
                            item.title,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            val scope = rememberCoroutineScope()
            val isDarkTheme by themePreferences.isDarkMode.collectAsState(false)

            Switch(
                checked = isDarkTheme ?: false,
                onCheckedChange = {
                    scope.launch {
                        themePreferences.changeDarkMode(it)
                    }
                }
            )
        }
    }
}
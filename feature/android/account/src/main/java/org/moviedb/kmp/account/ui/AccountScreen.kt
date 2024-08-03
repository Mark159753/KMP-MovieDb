package org.moviedb.kmp.account.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.icerock.moko.resources.compose.stringResource
import org.koin.androidx.compose.koinViewModel
import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.components.PreferenceSwitchRow
import org.moviedb.kmp.ui.dialogs.ChangeLanguageDialog
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = koinViewModel()
){

    val isInDarkModel by viewModel.isDarkTheme.collectAsStateWithLifecycle(initialValue = isSystemInDarkTheme())
    val currentLng by viewModel.currentLng.collectAsStateWithLifecycle(initialValue = AppLanguage.EN)

    var displayLanguageDialog by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    if (displayLanguageDialog){
        ChangeLanguageDialog(
            current = currentLng,
            onSelect = remember {
                {
                    viewModel.onChangeLanguage(it, context)
                    displayLanguageDialog = false
                }
            },
            onDismiss = { displayLanguageDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.localColors.background)
            .statusBarsPadding()
    ){
        PreferenceSwitchRow(
            isSelected = isInDarkModel,
            onCheckedChange = viewModel::onIsDarkModelToggle,
            text = stringResource(resource = MR.strings.account_screen_use_dark_mode_title),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Text(
            text = stringResource(resource = MR.strings.account_screen_language_title),
            style = MaterialTheme.localTypography.h2,
            color = MaterialTheme.localColors.onSurface,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { displayLanguageDialog = true }
                .padding(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
private fun AccountScreenPreview(){

}
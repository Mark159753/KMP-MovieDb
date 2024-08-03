package org.moviedb.kmp.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.icerock.moko.resources.compose.stringResource
import org.moviedb.kmp.common.constants.AppLanguage
import org.moviedb.kmp.ui.MR
import org.moviedb.kmp.ui.extension.displayName
import org.moviedb.kmp.ui.theme.localColors
import org.moviedb.kmp.ui.theme.localTypography

@Composable
fun ChangeLanguageDialog(
    current:AppLanguage = AppLanguage.EN,
    onSelect:(AppLanguage)->Unit = {},
    onDismiss:()->Unit = {}
){
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ){
        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(20.dp),
            backgroundColor = MaterialTheme.localColors.surfaceVariant
        ){
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(MR.strings.account_screen_language_title),
                    style = MaterialTheme.localTypography.h1.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.localColors.onSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.height(24.dp))

                AppLanguage.entries.forEach { item ->
                    LanguageItem(
                        selected = current == item,
                        onSelect = onSelect,
                        item = item
                    )
                }

            }
        }
    }
}

@Composable
private fun LanguageItem(
    modifier: Modifier = Modifier,
    selected:Boolean,
    item:AppLanguage,
    onSelect:(AppLanguage)->Unit
){
    Text(
        text = stringResource(item.displayName()),
        style = MaterialTheme.localTypography.bodyMedium,
        color = if (selected) MaterialTheme.localColors.primary else MaterialTheme.localColors.onSurfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSelect(item) }
            .padding(vertical = 10.dp, horizontal = 14.dp)
    )
}
package com.urano_dev.petstore.theme.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.urano_dev.petstore.theme.domain.ThemeType

@Composable
fun ThemeModifier(
    onChange: (ThemeType) -> Unit
) {
    Row {
        Icon(
            Icons.Default.Done,
            contentDescription = "",
            modifier = Modifier.clickable {
                onChange.invoke(ThemeType.LIGHT)
            }
        )
        Icon(
            Icons.Default.Build,
            contentDescription = "",
            modifier = Modifier.clickable {
                onChange.invoke(ThemeType.DARK)
            }
        )
    }
}


@Composable
fun ThemeToggler(
    onToggle: ()->Unit,
) {
    Icon(
        Icons.Default.Refresh,
        contentDescription = "",
        modifier = Modifier
            .clickable(onClick = onToggle)
    )
}
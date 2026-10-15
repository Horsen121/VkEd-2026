package com.myproject.vked2026.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.myproject.vked2026.domain.AppItem

@Composable
fun AppElement(
    app: AppItem,
    onElementClick: (String) -> Unit,
) {
    Row(
        horizontalArrangement = spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground,
                Shapes().medium
            )
            .clickable(onClick =  { onElementClick(app.id) } )
    ) {
        Icon(painterResource(app.image), null)
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val appName = "${app.name}${app.id}"
            Text("$appName - ${app.category}")
            Text("$appName ${app.description}", fontSize = TextUnit(12f, TextUnitType.Sp))
        }
    }
}
package com.myproject.vked2026.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myproject.vked2026.R
import com.myproject.vked2026.ui.theme.VkEd2026Theme

private data class AppItem(
    val id: String,
    val image: Int,
    val name: String,
    val description: String,
    val category: String,
)

private val appItems = listOf(
    AppItem("1", R.drawable.ic_launcher_foreground, "App1", "App1 description", "Game"),
    AppItem("2", R.drawable.ic_launcher_foreground, "App2", "App2 description", "Tool"),
    AppItem("3", R.drawable.ic_launcher_foreground, "App3", "App3 description", "Game"),
    AppItem("4", R.drawable.ic_launcher_foreground, "App4", "App4 description", "Tool"),
    AppItem("5", R.drawable.ic_launcher_foreground, "App5", "App5 description", "Tool"),
)

@Composable
fun AppListScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {
        ToolBar()

        AppList(appItems)
    }
}

@Composable
private fun ToolBar(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(painterResource(R.drawable.ic_launcher_foreground), null)
    }
}

@Composable
private fun AppList(
    data: List<AppItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(items = data, key = { it.id }) { app ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onBackground,
                        Shapes().medium
                    )
            ) {
                Icon(painterResource(app.image), null)
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text("${app.name} - ${app.category}")
                    Text(app.description, fontSize = TextUnit(12f, TextUnitType.Sp))
                }
            }
        }
    }
}


@Preview
@Composable
private fun ToolBarPreview() {
    VkEd2026Theme(darkTheme = false, dynamicColor = false) {
        Scaffold(
            contentWindowInsets = WindowInsets(left = 16.dp, right = 16.dp, top = 32.dp),
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            ToolBar()
        }
    }
}

@Preview
@Composable
private fun AppListPreview() {
    VkEd2026Theme(darkTheme = false, dynamicColor = false) {
        Scaffold(
            contentWindowInsets = WindowInsets(left = 16.dp, right = 16.dp, top = 32.dp),
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            AppList(appItems)
        }
    }
}

@Preview
@Composable
private fun AppListScreenPreview() {
    VkEd2026Theme(darkTheme = false, dynamicColor = false) {
        Scaffold(
            contentWindowInsets = WindowInsets(left = 16.dp, right = 16.dp, top = 32.dp),
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            AppListScreen()
        }
    }
}
package com.myproject.vked2026.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myproject.vked2026.R
import com.myproject.vked2026.domain.models.AppItem
import com.myproject.vked2026.presentation.viewmodels.AppListViewModel
import com.myproject.vked2026.ui.components.AppElement
import com.myproject.vked2026.ui.theme.VkEd2026Theme
import kotlinx.serialization.Serializable

@Serializable
data object AppListRoute{ const val ROUTE: String = "AppListRoute" }

@Composable
fun AppListScreenRoute(
    viewModel: AppListViewModel = hiltViewModel(),
    onElementClick: (String) -> Unit,
) {
    val appItems by viewModel.appItems.collectAsStateWithLifecycle()

    AppListScreen(
        appItems,
        onElementClick
    )
}

@Composable
private fun AppListScreen(
    appItems: List<AppItem>,
    onElementClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {
        ToolBar()

        AppList(appItems, onElementClick)
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
    onElementClick: (String) -> Unit,
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
            AppElement(app, onElementClick)
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
            AppList(listOf(), {})
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
            AppListScreen(emptyList(), onElementClick = {})
        }
    }
}
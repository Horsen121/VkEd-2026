package com.myproject.vked2026.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.myproject.vked2026.R
import com.myproject.vked2026.domain.AppItem
import com.myproject.vked2026.ui.components.AppElement
import com.myproject.vked2026.ui.theme.VkEd2026Theme
import com.myproject.vked2026.ui.viewmodels.AppDetailsViewModel
import kotlinx.serialization.Serializable

@Serializable
data object AppDetailsRoute { const val ROUTE: String = "AppDetailsRoute" }

@Composable
fun AppDetailsScreenRoute(
    appId: String,
    toAppList: () -> Unit,
    viewModel: AppDetailsViewModel = AppDetailsViewModel()
) {
    AppDetails(appId, toAppList, viewModel)
}

@Composable
private fun AppDetails(
    appId: String,
    toAppList: () -> Unit,
    viewModel: AppDetailsViewModel,
    modifier: Modifier = Modifier
) {
    val appItems by viewModel.appItems.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {
        Text("App:$appId details screen")

        Showcase(
            viewModel.pageSizeCount,
            appItems,
            toAppList
        )
    }
}

@Composable
private fun Showcase(
    pageSizeCount: Int,
    appItems: List<AppItem>,
    toAppList: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = remember { PagerState { 3 } }
    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            for (i in (pagerState.currentPage*pageSizeCount)..<(pagerState.currentPage*pageSizeCount + pageSizeCount))
            AppElement(appItems[i]) { toAppList() }
        }
    }
}


@Preview
@Composable
private fun ShowcasePreview() {
    VkEd2026Theme(darkTheme = false, dynamicColor = false) {
        Scaffold(
            contentWindowInsets = WindowInsets(left = 16.dp, right = 16.dp, top = 32.dp),
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            Showcase(3, listOf(),{})
        }
    }
}
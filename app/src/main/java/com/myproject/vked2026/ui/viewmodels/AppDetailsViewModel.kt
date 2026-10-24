package com.myproject.vked2026.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.myproject.vked2026.R
import com.myproject.vked2026.domain.AppItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppDetailsViewModel(

): ViewModel() {
    val pageSizeCount = 3

    private val _appItems = MutableStateFlow( buildList {
        val appItem = AppItem(
            "1",
            R.drawable.ic_launcher_foreground,
            "App",
            "description",
            "Game"
        )

        repeat(10) {
            add(appItem.copy(id = it.toString()))
        }
    })
    val appItems: StateFlow<List<AppItem>> = _appItems.asStateFlow()
}
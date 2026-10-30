package com.myproject.vked2026.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.vked2026.domain.models.AppItem
import com.myproject.vked2026.domain.usecases.GetAppItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppItemsUseCase: GetAppItemsUseCase
): ViewModel() {
    val pageSizeCount = 3

    private val _appItems = MutableStateFlow<List<AppItem>>( emptyList())
    val appItems: StateFlow<List<AppItem>> = _appItems.asStateFlow()

    init {
        loadAppItems()
    }

    private fun loadAppItems() {
        viewModelScope.launch {
            val list = getAppItemsUseCase().first()
            _appItems.value = list
        }
    }
}
package com.myproject.vked2026.domain.repository

import com.myproject.vked2026.domain.models.AppItem
import kotlinx.coroutines.flow.Flow

interface AppItemRepository {
    fun getApps(): Flow<List<AppItem>>
}
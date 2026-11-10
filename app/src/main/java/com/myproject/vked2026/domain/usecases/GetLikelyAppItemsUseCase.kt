package com.myproject.vked2026.domain.usecases

import com.myproject.vked2026.domain.models.AppItem
import com.myproject.vked2026.domain.repository.AppItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take

class GetLikelyAppItemsUseCase(
    private val appItemRepository: AppItemRepository
) {
    suspend operator fun invoke(): Flow<List<AppItem>> {
        return appItemRepository.getApps().take(9)
    }
}
package com.myproject.vked2026.data.repository

import com.myproject.vked2026.data.datasource.local.dao.AppItemDao
import com.myproject.vked2026.data.datasource.remote.RemoteApi
import com.myproject.vked2026.data.mappers.toDomain
import com.myproject.vked2026.domain.models.AppItem
import com.myproject.vked2026.domain.repository.AppItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AppItemRepositoryImpl @Inject constructor(
    private val appItemDao: AppItemDao,
    private val remoteApi: RemoteApi
): AppItemRepository {

    override fun getApps(): Flow<List<AppItem>> = flow {
        emit(loadApps())
    }

    private suspend fun loadApps(): List<AppItem> {
        val remoteApps = remoteApi.getApps() //try {
            remoteApi.getApps()
//        } catch (e: IOException) {
//            val cachedApps = appItemDao.getApps()
//
//            return cachedApps.map { it.toDomain() }
//        }

        return remoteApps.map { it.toDomain() }
    }
}
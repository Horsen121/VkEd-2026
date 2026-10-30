package com.myproject.vked2026.data.datasource.remote

import com.myproject.vked2026.R
import com.myproject.vked2026.data.dto.AppItemDto

interface RemoteApi {
    suspend fun getApps(): List<AppItemDto>
}

object RemoteApiImpl: RemoteApi {
    override suspend fun getApps(): List<AppItemDto> {
        val appItem = AppItemDto(
            "1",
            "App",
            "description",
            R.drawable.ic_launcher_foreground,
            "Game",
        )

        val apps = List(10) { index ->
            appItem.copy(id = index.toString())
        }

        return apps
    }
}
package com.myproject.vked2026.data.datasource.remote

import com.myproject.vked2026.data.dto.AppDetailsDto
import com.myproject.vked2026.data.dto.AppItemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {

    @GET("catalog")
    suspend fun getApps(): Response<List<AppItemDto>>

    @GET("{id}")
    suspend fun getAppDetails(@Query("id") id: String): Response<AppDetailsDto>
}
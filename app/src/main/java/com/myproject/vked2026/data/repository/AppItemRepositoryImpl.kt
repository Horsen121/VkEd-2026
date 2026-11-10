package com.myproject.vked2026.data.repository

import android.app.Application
import android.util.Log
import com.myproject.vked2026.data.datasource.local.dao.AppItemDao
import com.myproject.vked2026.data.datasource.remote.RemoteApi
import com.myproject.vked2026.data.mappers.toDomain
import com.myproject.vked2026.data.mappers.toEntity
import com.myproject.vked2026.domain.models.AppItem
import com.myproject.vked2026.domain.repository.AppItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.io.IOException
import javax.inject.Inject

class AppItemRepositoryImpl @Inject constructor(
    private val appItemDao: AppItemDao,
): AppItemRepository {

    companion object {
        private const val BASE_URL = "http://185.103.109.134/"
    }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val service = retrofit.create(RemoteApi::class.java)

    override suspend fun getApps(): Flow<List<AppItem>> {
        var apps = emptyList<AppItem>()

        withContext(Dispatchers.IO) {
            apps = appItemDao.getApps().first().map { app -> app.toDomain() }
//            apps = emptyList()

            try {
                service.getApps().let { response ->
                    if (response.isSuccessful && response.body() != null) {
                        val appItems = response.body()!!.map { it.toDomain() }
                        appItemDao.insertApps(response.body()!!.map { it.toEntity() })
                        apps = appItems
                    }
                }
            } catch (e: IOException) {
                Log.e("TAG", "getApps: ${e.message}",)
            }
        }

        return flowOf(apps)
    }
}
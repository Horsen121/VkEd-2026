package com.myproject.vked2026.di

import android.content.Context
import androidx.room.Room
import com.myproject.vked2026.data.datasource.local.LocalDatabase
import com.myproject.vked2026.data.datasource.local.dao.AppItemDao
import com.myproject.vked2026.data.datasource.remote.RemoteApi
import com.myproject.vked2026.data.datasource.remote.RemoteApiImpl
import com.myproject.vked2026.data.repository.AppItemRepositoryImpl
import com.myproject.vked2026.domain.repository.AppItemRepository
import com.myproject.vked2026.domain.usecases.GetAppItemsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppItemProvidesModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "app_database.db",
        ).build()
    }

    @Provides
    fun provideAppItemDao(database: LocalDatabase, ): AppItemDao {
        return database.appItemDao()
    }

    @Provides
    fun provideRemoteApi(): RemoteApi = RemoteApiImpl

    @Provides
    fun getAppItemsUseCase(repository: AppItemRepository): GetAppItemsUseCase {
        return GetAppItemsUseCase(repository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppItemBindsModule {

    @Binds
    abstract fun provideAppItemRepository(appItemRepositoryImpl: AppItemRepositoryImpl): AppItemRepository

}
package com.myproject.vked2026.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.myproject.vked2026.data.datasource.local.entity.AppItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppItemDao {

    @Query("SELECT * FROM app_items")
    fun getApps(): Flow<List<AppItemEntity>>

    @Insert(onConflict = REPLACE)
    fun insertApps(apps: List<AppItemEntity>)
}
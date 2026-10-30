package com.myproject.vked2026.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.myproject.vked2026.data.datasource.local.entity.AppItemEntity

@Dao
interface AppItemDao {

    @Query("SELECT * FROM app_items")
    fun getApps(): List<AppItemEntity>
}
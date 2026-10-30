package com.myproject.vked2026.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_items")
data class AppItemEntity(
    @PrimaryKey val id: String,
    val image: Int,
    val name: String,
    val description: String,
    val category: String,
)
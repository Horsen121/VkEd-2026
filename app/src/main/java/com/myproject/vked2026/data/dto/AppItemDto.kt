package com.myproject.vked2026.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppItemDto(
    val id: String,
    val title: String,
    val description: String,
    val picture: Int,
    val category: String,
)


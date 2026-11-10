package com.myproject.vked2026.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppItemDto(
    val id: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val category: String,
)


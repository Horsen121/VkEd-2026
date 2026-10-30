package com.myproject.vked2026.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AppItem(
    val id: String,
    val image: Int,
    val name: String,
    val description: String,
    val category: String,
)
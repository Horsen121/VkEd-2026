package com.myproject.vked2026.data.mappers

import com.myproject.vked2026.data.datasource.local.entity.AppItemEntity
import com.myproject.vked2026.data.dto.AppItemDto
import com.myproject.vked2026.domain.models.AppItem


fun AppItemDto.toDomain(): AppItem {
    return AppItem(
        id = this.id,
        image = this.picture,
        name = this.title,
        description = this.description,
        category = this.category
    )
}

fun AppItemEntity.toDomain(): AppItem {
    return AppItem(
        id = this.id,
        image = this.image,
        name = this.name,
        description = this.description,
        category = this.category
    )
}

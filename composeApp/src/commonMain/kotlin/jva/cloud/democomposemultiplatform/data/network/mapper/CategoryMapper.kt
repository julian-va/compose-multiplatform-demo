package jva.cloud.democomposemultiplatform.data.network.mapper

import jva.cloud.democomposemultiplatform.data.network.entity.CategoryNetworkEntity
import jva.cloud.democomposemultiplatform.domain.model.Category

fun CategoryNetworkEntity.toDomain(): Category = Category(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    slug = slug,
    updatedAt = updatedAt
)

fun Category.toNetwork(): CategoryNetworkEntity = CategoryNetworkEntity(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    slug = slug,
    updatedAt = updatedAt
)
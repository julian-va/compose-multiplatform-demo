package jva.cloud.democomposemultiplatform.data.network.mapper

import jva.cloud.democomposemultiplatform.data.network.entity.ProductNetworkEntity
import jva.cloud.democomposemultiplatform.domain.model.Product

fun ProductNetworkEntity.toDomain(): Product = Product(
    category = category.toDomain(),
    creationAt = creationAt,
    description = description,
    id = id,
    images = images,
    price = price,
    slug = slug,
    title = title,
    updatedAt = updatedAt
)

fun Product.toNetwork(): ProductNetworkEntity = ProductNetworkEntity(
    category = category.toNetwork(),
    creationAt = creationAt,
    description = description,
    id = id,
    images = images,
    price = price,
    slug = slug,
    title = title,
    updatedAt = updatedAt
)
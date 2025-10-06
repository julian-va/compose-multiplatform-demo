package jva.cloud.democomposemultiplatform.data.network.mapper

import jva.cloud.democomposemultiplatform.data.network.entity.CreatedUserNetworkEntity
import jva.cloud.democomposemultiplatform.domain.model.CreatedUser

fun CreatedUserNetworkEntity.toDomain(): CreatedUser = CreatedUser(
    id = id,
    name = name,
    email = email,
    password = password,
    role = role,
    avatar = avatar,
    creationAt = creationAt,
    updatedAt = updatedAt
)

fun CreatedUser.toEntity(): CreatedUserNetworkEntity = CreatedUserNetworkEntity(
    id = id,
    name = name,
    email = email,
    password = password,
    role = role,
    avatar = avatar,
    creationAt = creationAt,
    updatedAt = updatedAt
)
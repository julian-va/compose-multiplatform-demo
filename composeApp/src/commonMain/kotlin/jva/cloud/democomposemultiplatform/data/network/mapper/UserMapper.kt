package jva.cloud.democomposemultiplatform.data.network.mapper

import jva.cloud.democomposemultiplatform.data.network.entity.UserNetworkEntity
import jva.cloud.democomposemultiplatform.domain.model.User

fun UserNetworkEntity.toDomain(): User = User(
    name = name,
    email = email,
    password = password,
    avatar = avatar
)

fun User.toEntity(): UserNetworkEntity = UserNetworkEntity(
    name = name,
    email = email,
    password = password,
    avatar = avatar
)
package jva.cloud.democomposemultiplatform.data.network.mapper

import jva.cloud.democomposemultiplatform.data.network.entity.TokenNetworkEntity
import jva.cloud.democomposemultiplatform.domain.model.Token

fun TokenNetworkEntity.toDomain(): Token {
    return Token(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}

fun Token.toNetwork(): TokenNetworkEntity {
    return TokenNetworkEntity(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken
    )
}

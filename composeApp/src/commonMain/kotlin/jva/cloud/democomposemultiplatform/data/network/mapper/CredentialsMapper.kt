package jva.cloud.democomposemultiplatform.data.network.mapper

import jva.cloud.democomposemultiplatform.data.network.entity.CredentialsNetworkEntity
import jva.cloud.democomposemultiplatform.domain.model.Credentials

fun CredentialsNetworkEntity.toDomain(): Credentials {
    return Credentials(
        email = this.email,
        password = this.password
    )
}

fun Credentials.toNetwork(): CredentialsNetworkEntity {
    return CredentialsNetworkEntity(
        email = this.email,
        password = this.password
    )
}

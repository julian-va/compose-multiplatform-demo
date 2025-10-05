package jva.cloud.democomposemultiplatform.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CredentialsNetworkEntity(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String
)

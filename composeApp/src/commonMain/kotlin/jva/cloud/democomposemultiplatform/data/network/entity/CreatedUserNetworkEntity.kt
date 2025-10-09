package jva.cloud.democomposemultiplatform.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedUserNetworkEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("role")
    val role: String,
    @SerialName("avatar")
    val avatar: String,
    @SerialName("creationAt")
    val creationAt: String,
    @SerialName("updatedAt")
    val updatedAt: String
)
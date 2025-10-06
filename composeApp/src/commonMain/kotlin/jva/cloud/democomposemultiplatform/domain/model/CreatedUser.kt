package jva.cloud.democomposemultiplatform.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CreatedUser(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val avatar: String,
    val creationAt: String,
    val updatedAt: String
)
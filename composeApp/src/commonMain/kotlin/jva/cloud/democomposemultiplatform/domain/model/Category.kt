package jva.cloud.democomposemultiplatform.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val creationAt: String,
    val id: Int,
    val image: String,
    val name: String,
    val slug: String,
    val updatedAt: String
)
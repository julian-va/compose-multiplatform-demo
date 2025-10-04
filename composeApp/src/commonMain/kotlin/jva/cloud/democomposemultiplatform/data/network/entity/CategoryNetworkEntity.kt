package jva.cloud.democomposemultiplatform.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryNetworkEntity(
    @SerialName("creationAt")
    val creationAt: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("updatedAt")
    val updatedAt: String
)
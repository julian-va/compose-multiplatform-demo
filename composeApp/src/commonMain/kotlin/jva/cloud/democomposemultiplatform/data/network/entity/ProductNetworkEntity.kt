package jva.cloud.democomposemultiplatform.data.network.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductNetworkEntity(
    @SerialName("category")
    val category: CategoryNetworkEntity,
    @SerialName("creationAt")
    val creationAt: String,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: List<String>,
    @SerialName("price")
    val price: Int,
    @SerialName("slug")
    val slug: String,
    @SerialName("title")
    val title: String,
    @SerialName("updatedAt")
    val updatedAt: String
)
package jva.cloud.democomposemultiplatform.data.network.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import jva.cloud.democomposemultiplatform.data.network.entity.ProductNetworkEntity
import jva.cloud.democomposemultiplatform.data.network.mapper.toDomain
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository

class ProductNetworkRepositoryImpl(private val client: HttpClient) : ProductNetworkRepository {
    override suspend fun retrieveAllProducts(): List<Product> {
        val response: HttpResponse =
            client.get(urlString = "products")
        if (response.status.value in 200..299) {
            var productNetworkEntity: List<ProductNetworkEntity> = response.body()
            return productNetworkEntity.map { productNetworkEntity -> productNetworkEntity.toDomain() }
        } else {
            throw Exception("Error: ${response.status.value}")
        }
    }
}
package jva.cloud.democomposemultiplatform.data.network.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import jva.cloud.democomposemultiplatform.data.network.entity.ProductNetworkEntity
import jva.cloud.democomposemultiplatform.data.network.mapper.toDomain
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository
import jva.cloud.democomposemultiplatform.utils.ConstantApp.ENDPOINT_PRODUCTS

class ProductNetworkRepositoryImpl(private val client: HttpClient) : ProductNetworkRepository {

    companion object {
        const val ERROR_FETCHING_PRODUCT = "Error fetching product. Status:"
    }

    override suspend fun retrieveAllProducts(): List<Product> {
        val response: HttpResponse =
            client.get(urlString = ENDPOINT_PRODUCTS)
        if (response.status.isSuccess()) {
            val productNetworkEntity: List<ProductNetworkEntity> = response.body()
            return productNetworkEntity.map { productNetworkEntity -> productNetworkEntity.toDomain() }
        } else {
            throw Exception("Error: ${response.status.value}")
        }
    }

    override suspend fun retrieveProductById(id: Int): Result<Product> {
        return try {
            val response: HttpResponse = client.get(urlString = "$ENDPOINT_PRODUCTS/$id")

            if (response.status.isSuccess()) {
                val productNetworkEntity: ProductNetworkEntity = response.body()
                Result.success(value = productNetworkEntity.toDomain())
            } else {
                Result.failure(Exception("$ERROR_FETCHING_PRODUCT ${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(exception = e)
        }
    }
}
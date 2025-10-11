package jva.cloud.democomposemultiplatform.domain.repository

import jva.cloud.democomposemultiplatform.domain.model.Product

interface ProductNetworkRepository {
    suspend fun retrieveAllProducts(): Result<List<Product>>
    suspend fun retrieveProductById(id: Int): Result<Product>
}

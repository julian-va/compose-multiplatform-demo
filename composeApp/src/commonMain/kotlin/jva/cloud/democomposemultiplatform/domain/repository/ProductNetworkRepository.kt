package jva.cloud.democomposemultiplatform.domain.repository

import jva.cloud.democomposemultiplatform.domain.model.Product

interface ProductNetworkRepository {
    suspend fun retrieveAllProducts(): List<Product>
    suspend fun retrieveProductById(id: Int): Result<Product>
}

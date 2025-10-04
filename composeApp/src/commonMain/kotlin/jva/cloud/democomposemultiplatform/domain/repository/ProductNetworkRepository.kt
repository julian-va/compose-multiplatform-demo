package jva.cloud.democomposemultiplatform.domain.repository

import jva.cloud.democomposemultiplatform.domain.model.Product

interface ProductNetworkRepository {
    suspend fun retrieveAllProducts(): List<Product>
}

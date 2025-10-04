package jva.cloud.democomposemultiplatform.domain.usecase

import jva.cloud.democomposemultiplatform.domain.model.Product

interface RetrieverAllProductFromRemote {
    suspend fun retrieveAllProducts(): List<Product>

}
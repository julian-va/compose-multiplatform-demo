package jva.cloud.democomposemultiplatform.domain.usecase

import jva.cloud.democomposemultiplatform.domain.model.Product

interface RetrieverProductFromRemote {
    suspend fun retrieveProductById(id: Int): Result<Product>
}


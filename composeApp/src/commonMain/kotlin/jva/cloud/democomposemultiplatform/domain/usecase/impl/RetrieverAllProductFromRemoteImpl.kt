package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverAllProductFromRemote

class RetrieverAllProductFromRemoteImpl(private val productNetworkRepository: ProductNetworkRepository) :
    RetrieverAllProductFromRemote {
    override suspend fun retrieveAllProducts(): List<Product> {
        return productNetworkRepository.retrieveAllProducts()
    }
}
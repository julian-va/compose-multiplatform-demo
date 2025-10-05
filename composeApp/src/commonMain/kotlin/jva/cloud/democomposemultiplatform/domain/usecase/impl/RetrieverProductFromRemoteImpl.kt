package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverProductFromRemote

class RetrieverProductFromRemoteImpl(private val productNetworkRepository: ProductNetworkRepository) :
    RetrieverProductFromRemote {
    override suspend fun retrieveProductById(id: Int): Result<Product> {
        return productNetworkRepository.retrieveProductById(id)
    
    }
}
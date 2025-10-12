package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.DeleteProduct

class DeleteProductImpl(private val productRepository: ProductNetworkRepository) : DeleteProduct {
    override suspend fun deleteProductById(id: Int): Result<Unit> {
        return productRepository.deleteProductById(id = id)
    }
}
package jva.cloud.democomposemultiplatform.domain.usecase

interface DeleteProduct {
    suspend fun deleteProductById(id: Int): Result<Unit>
}
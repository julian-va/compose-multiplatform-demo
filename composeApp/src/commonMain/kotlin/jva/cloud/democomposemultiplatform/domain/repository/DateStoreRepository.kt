package jva.cloud.democomposemultiplatform.domain.repository

interface DateStoreRepository {
    suspend fun save(key: String, value: String)
    suspend fun read(key: String): String?
    suspend fun deleted(key: String)
}
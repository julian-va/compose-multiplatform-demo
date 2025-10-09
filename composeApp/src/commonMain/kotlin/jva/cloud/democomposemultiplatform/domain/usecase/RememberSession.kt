package jva.cloud.democomposemultiplatform.domain.usecase

interface RememberSession {
    suspend fun setRememberSession(remember: Boolean)
    suspend fun getRememberSession(): Boolean
}

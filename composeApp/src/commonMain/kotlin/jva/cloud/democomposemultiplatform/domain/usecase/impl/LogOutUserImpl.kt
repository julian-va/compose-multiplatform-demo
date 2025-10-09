package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.repository.DateStoreRepository
import jva.cloud.democomposemultiplatform.domain.usecase.LogOutUser
import jva.cloud.democomposemultiplatform.domain.usecase.RememberSession
import jva.cloud.democomposemultiplatform.utils.ConstantApp.TOKEN_KEY

class LogOutUserImpl(
    private val dateStoreRepository: DateStoreRepository,
    private val rememberSession: RememberSession
) : LogOutUser {
    override suspend fun logOutUser() {
        dateStoreRepository.deleted(key = TOKEN_KEY)
        rememberSession.setRememberSession(remember = false)
    }
}
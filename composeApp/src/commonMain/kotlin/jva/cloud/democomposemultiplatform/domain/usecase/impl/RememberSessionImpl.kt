package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.repository.DateStoreRepository
import jva.cloud.democomposemultiplatform.domain.usecase.RememberSession
import jva.cloud.democomposemultiplatform.utils.ConstantApp.REMEMBER_SESSION_FALSE
import jva.cloud.democomposemultiplatform.utils.ConstantApp.REMEMBER_SESSION_KEY
import jva.cloud.democomposemultiplatform.utils.ConstantApp.REMEMBER_SESSION_TRUE

class RememberSessionImpl(private val dataStoreRepository: DateStoreRepository) : RememberSession {
    override suspend fun setRememberSession(remember: Boolean) {
        if (remember) {
            dataStoreRepository.save(key = REMEMBER_SESSION_KEY, value = REMEMBER_SESSION_TRUE)
        } else {
            dataStoreRepository.save(key = REMEMBER_SESSION_KEY, value = REMEMBER_SESSION_FALSE)
        }
    }

    override suspend fun getRememberSession(): Boolean {
        return dataStoreRepository.read(key = REMEMBER_SESSION_KEY) == REMEMBER_SESSION_TRUE
    }

}
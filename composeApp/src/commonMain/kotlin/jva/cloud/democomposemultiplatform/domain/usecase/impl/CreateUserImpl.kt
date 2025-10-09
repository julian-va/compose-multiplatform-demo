package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.model.CreatedUser
import jva.cloud.democomposemultiplatform.domain.model.User
import jva.cloud.democomposemultiplatform.domain.repository.DateStoreRepository
import jva.cloud.democomposemultiplatform.domain.repository.UserNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.CreateUser
import jva.cloud.democomposemultiplatform.utils.ConstantApp.CURRENT_PASSWORD_KEY
import jva.cloud.democomposemultiplatform.utils.ConstantApp.CURRENT_USER_KEY

class CreateUserImpl(
    private val userNetworkRepository: UserNetworkRepository,
    private val dateStoreRepository: DateStoreRepository
) : CreateUser {
    override suspend fun createUser(user: User): Result<CreatedUser> {
        val creationResult: Result<CreatedUser> = userNetworkRepository.createUser(user = user)
        creationResult.onSuccess {
            dateStoreRepository.save(key = CURRENT_USER_KEY, value = user.email)
            dateStoreRepository.save(key = CURRENT_PASSWORD_KEY, value = user.password)
        }
        return creationResult
    }
}
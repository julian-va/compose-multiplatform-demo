package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.model.CreatedUser
import jva.cloud.democomposemultiplatform.domain.model.User
import jva.cloud.democomposemultiplatform.domain.repository.UserNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.CreateUser

class CreateUserImpl(private val userNetworkRepository: UserNetworkRepository) : CreateUser {
    override suspend fun createUser(user: User): Result<CreatedUser> {
        return userNetworkRepository.createUser(user = user)
    }
}
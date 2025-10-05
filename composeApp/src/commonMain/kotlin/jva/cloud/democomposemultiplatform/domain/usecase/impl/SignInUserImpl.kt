package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token
import jva.cloud.democomposemultiplatform.domain.repository.UserNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.SignInUser

class SignInUserImpl(private val userNetworkRepository: UserNetworkRepository) : SignInUser {
    override suspend fun invoke(credentials: Credentials): Result<Token> {
        return userNetworkRepository.login(credentials = credentials)
    }

}
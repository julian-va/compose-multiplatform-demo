package jva.cloud.democomposemultiplatform.domain.usecase

import jva.cloud.democomposemultiplatform.domain.model.CreatedUser
import jva.cloud.democomposemultiplatform.domain.model.User

interface CreateUser {
    suspend fun createUser(user: User): Result<CreatedUser>

}
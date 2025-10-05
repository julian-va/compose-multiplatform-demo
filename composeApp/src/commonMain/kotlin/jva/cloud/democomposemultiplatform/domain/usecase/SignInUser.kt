package jva.cloud.democomposemultiplatform.domain.usecase

import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token

interface SignInUser {
    suspend operator fun invoke(credentials: Credentials): Result<Token>
}

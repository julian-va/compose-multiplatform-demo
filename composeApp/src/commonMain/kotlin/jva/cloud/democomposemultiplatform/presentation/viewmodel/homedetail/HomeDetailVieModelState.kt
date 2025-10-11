package jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail

import jva.cloud.democomposemultiplatform.domain.model.Product

data class HomeDetailVieModelState(
    val isLoading: Boolean = true,
    val product: Product? = null,
    val error: Boolean = false
)

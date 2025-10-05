package jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail

import jva.cloud.democomposemultiplatform.domain.model.Product

data class HomeDetailVieModelState(
    val isLoading: Boolean = true,
    val products: Product? = null,
    val error: String? = null
)

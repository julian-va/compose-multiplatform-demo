package jva.cloud.democomposemultiplatform.presentation.viewmodel.home

import jva.cloud.democomposemultiplatform.domain.model.Product

data class HomeVieModelState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

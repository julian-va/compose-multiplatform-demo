@file:OptIn(ExperimentalMaterial3Api::class)

package jva.cloud.democomposemultiplatform.presentation.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.ic_broken_image
import democomposemultiplatform.composeapp.generated.resources.log_out_button
import democomposemultiplatform.composeapp.generated.resources.products_title
import jva.cloud.democomposemultiplatform.domain.model.Category
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.presentation.components.ErrorView
import jva.cloud.democomposemultiplatform.presentation.components.LoadingIndicator
import jva.cloud.democomposemultiplatform.presentation.viewmodel.home.HomeVieMode
import jva.cloud.democomposemultiplatform.presentation.viewmodel.home.HomeVieModelState
import jva.cloud.democomposemultiplatform.utils.UtilsApp.DEFAULT_IMAGE
import jva.cloud.democomposemultiplatform.utils.UtilsApp.reprocessImageListFromApi
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object Home


@Composable
fun HomeView(goToDetail: (Int) -> Unit, goToLogin: () -> Unit, vm: HomeVieMode = koinViewModel()) {
    val state: HomeVieModelState = vm.state
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    if (state.error) {
        ErrorView(onRetry = { vm.logOut(goToLogin = goToLogin) })
    } else {
        HomeContent(
            state = state,
            goToDetail = goToDetail,
            logOutUser = { vm.logOut(goToLogin = goToLogin) },
            scrollBehavior = scrollBehavior,
            deleteProduct = { vm.deleteProduct(it) }
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeVieModelState,
    goToDetail: (Int) -> Unit,
    logOutUser: () -> Unit,
    deleteProduct: (Int) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.products_title)) },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = {
                        logOutUser()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = stringResource(Res.string.log_out_button)
                        )
                    }
                }
            )
        }) { paddingValues ->
        LoadingIndicator(enabled = state.isLoading, modifier = Modifier.padding(paddingValues))
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues),
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(items = state.products, key = { it.id }) { product: Product ->
                ProductItem(
                    goToDetail = goToDetail,
                    product = product,
                    deleteProduct = deleteProduct
                )
            }
        }
    }
}

@Composable
private fun ProductItem(
    goToDetail: (Int) -> Unit,
    product: Product,
    deleteProduct: (Int) -> Unit
) {
    Column(modifier = Modifier.clickable(onClick = { goToDetail(product.id) })) {
        Box(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = reprocessImageListFromApi(product.images),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().aspectRatio(2 / 3f)
                    .clip(MaterialTheme.shapes.small),
                error = painterResource(resource = Res.drawable.ic_broken_image)
            )
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { deleteProduct(product.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Product",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Text(text = product.title, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    val category = Category(
        id = 1,
        name = "Category 1",
        image = DEFAULT_IMAGE,
        creationAt = "",
        updatedAt = "",
        slug = ""
    )

    val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Product 1",
            images = listOf(DEFAULT_IMAGE, DEFAULT_IMAGE),
            price = "10.0",
            category = category,
            description = "Description 1",
            creationAt = "",
            updatedAt = "",
            slug = ""
        ),
        Product(
            id = 2,
            title = "Product 2",
            images = listOf(DEFAULT_IMAGE, DEFAULT_IMAGE),
            price = "10.0",
            category = category,
            description = "Description 1",
            creationAt = "",
            updatedAt = "",
            slug = ""
        )
    )

    HomeContent(
        state = HomeVieModelState(products = products),
        goToDetail = {},
        logOutUser = {},
        deleteProduct = {},
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}
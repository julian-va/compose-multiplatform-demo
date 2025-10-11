@file:OptIn(ExperimentalMaterial3Api::class)

package jva.cloud.democomposemultiplatform.presentation.views.homedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.back_content_description
import democomposemultiplatform.composeapp.generated.resources.ic_broken_image
import democomposemultiplatform.composeapp.generated.resources.product_details_title
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.presentation.components.ErrorView
import jva.cloud.democomposemultiplatform.presentation.components.LoadingIndicator
import jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail.HomeDetailVieModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail.HomeDetailVieModelState
import jva.cloud.democomposemultiplatform.utils.UtilsApp.reprocessImageFromApi
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Serializable
data class HomeDetail(val id: Int)


@Composable
fun HomeDetailView(vm: HomeDetailVieModel, onBack: () -> Unit) {
    val state: HomeDetailVieModelState = vm.state
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    
    if (state.error) {
        ErrorView(onRetry = onBack)
    } else {
        HomeDetailContent(scrollBehavior = scrollBehavior, state = state, onBack = onBack)
    }
}


@Composable
private fun HomeDetailContent(
    scrollBehavior: TopAppBarScrollBehavior,
    state: HomeDetailVieModelState,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.product_details_title)) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.back_content_description)
                        )
                    }
                })
        }) { paddingValues ->

        LoadingIndicator(enabled = state.isLoading, modifier = Modifier.padding(paddingValues))

        state.product?.let { productNotNull ->
            ProductDetailContent(product = productNotNull, paddingValues = paddingValues)
        }
    }
}


@Composable
private fun ProductDetailContent(product: Product, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                AsyncImage(
                    model = reprocessImageFromApi(product.images),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f),
                    error = painterResource(resource = Res.drawable.ic_broken_image)
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = product.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

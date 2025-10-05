package jva.cloud.democomposemultiplatform.presentation.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import democomposemultiplatform.composeapp.generated.resources.Res
import democomposemultiplatform.composeapp.generated.resources.products_title
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.presentation.components.LoadingIndicator
import jva.cloud.democomposemultiplatform.presentation.viewmodel.home.HomeVieMode
import jva.cloud.democomposemultiplatform.utils.UtilsApp.reprocessImageFromApi
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(goToDetail: (Int) -> Unit, vm: HomeVieMode = koinViewModel()) {
    val state = vm.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
            TopAppBar(
                title = { Text(text = stringResource(Res.string.products_title)) },
                scrollBehavior = scrollBehavior
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
                ProductItem(goToDetail = goToDetail, product = product)
            }
        }
    }
}

@Composable
private fun ProductItem(goToDetail: (Int) -> Unit, product: Product) {
    Column(modifier = Modifier.clickable(onClick = { goToDetail(product.id) })) {
        AsyncImage(
            model = reprocessImageFromApi(product.images),
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().aspectRatio(2 / 3f).clip(MaterialTheme.shapes.small)
        )
        Text(text = product.title, style = MaterialTheme.typography.titleMedium)
    }
}

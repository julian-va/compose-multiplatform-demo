package jva.cloud.democomposemultiplatform.presentation.views.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import jva.cloud.democomposemultiplatform.presentation.views.home.Home
import jva.cloud.democomposemultiplatform.presentation.views.home.HomeView
import jva.cloud.democomposemultiplatform.presentation.views.homedetail.HomeDetail
import jva.cloud.democomposemultiplatform.presentation.views.homedetail.HomeDetailView
import jva.cloud.democomposemultiplatform.presentation.views.login.Login
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home) {
        composable<Login> { Login() }

        composable<Home> { HomeView(goToDetail = { navController.navigate(HomeDetail(id = it)) }) }

        composable<HomeDetail> { backStackEntry ->
            val homeDetail = backStackEntry.toRoute<HomeDetail>()
            HomeDetailView(
                vm = koinViewModel(parameters = { parametersOf(homeDetail.id) }),
                onBack = { navController.navigate(Home) }
            )
        }
    }
}
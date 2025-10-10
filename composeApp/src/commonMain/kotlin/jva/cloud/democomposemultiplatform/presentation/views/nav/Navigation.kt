package jva.cloud.democomposemultiplatform.presentation.views.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import jva.cloud.democomposemultiplatform.presentation.views.createaccount.CreateAccount
import jva.cloud.democomposemultiplatform.presentation.views.createaccount.CreateAccountView
import jva.cloud.democomposemultiplatform.presentation.views.home.Home
import jva.cloud.democomposemultiplatform.presentation.views.home.HomeView
import jva.cloud.democomposemultiplatform.presentation.views.homedetail.HomeDetail
import jva.cloud.democomposemultiplatform.presentation.views.homedetail.HomeDetailView
import jva.cloud.democomposemultiplatform.presentation.views.login.Login
import jva.cloud.democomposemultiplatform.presentation.views.login.LoginView
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Login
    ) {
        composable<Login> {
            LoginView(
                redirectHome = {
                    navController.navigate(Home, builder = {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    })
                },
                redirectCreateAccount = { navController.navigate(CreateAccount) })
        }

        composable<CreateAccount>(enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(400)
            )
        }, exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(400)
            )
        }) {
            CreateAccountView(onSignIn = {
                navController.navigate(
                    Login,
                    builder = { popUpTo<CreateAccount> { inclusive = true } })
            })
        }

        composable<Home> {
            HomeView(
                goToDetail = { navController.navigate(HomeDetail(id = it)) },
                goToLogin = {
                    navController.navigate(Login, builder = {
                        popUpTo<Home> { inclusive = true }
                    })
                })
        }

        composable<HomeDetail>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(500)
                )
            }
        ) { backStackEntry ->
            val homeDetail = backStackEntry.toRoute<HomeDetail>()
            HomeDetailView(
                vm = koinViewModel(parameters = { parametersOf(homeDetail.id) }),
                onBack = { navController.popBackStack() }
            )
        }
    }
}
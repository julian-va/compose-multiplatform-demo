package jva.cloud.democomposemultiplatform.presentation.views.nav

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import jva.cloud.democomposemultiplatform.presentation.views.login.Login

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> { Login() }
    }
}
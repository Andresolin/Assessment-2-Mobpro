package org.d3if3002.miniproject2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3002.miniproject2.ui.Screen.AdminLanding
import org.d3if3002.miniproject2.ui.Screen.DetailScreen
import org.d3if3002.miniproject2.ui.Screen.KEY_ID_PEMESANAN
import org.d3if3002.miniproject2.ui.Screen.MainScreen



@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.AdminLanding.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }

        composable(route = Screen.AdminLanding.route){
            AdminLanding(navController)
        }

        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_PEMESANAN){ type = NavType.LongType}
            )
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_PEMESANAN)
            DetailScreen(navController, id)
        }

    }
}
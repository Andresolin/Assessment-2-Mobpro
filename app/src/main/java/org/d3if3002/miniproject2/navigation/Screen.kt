package org.d3if3002.miniproject2.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("mainscreen")
    data object FormBaru : Screen("detailScreen")
}
package org.d3if3002.miniproject2.navigation


import org.d3if3002.miniproject2.ui.Screen.KEY_ID_PEMESANAN
sealed class Screen(val route: String) {
    data object Home : Screen("mainscreen")
    data object FormBaru : Screen("detailScreen")

    data object FormUbah : Screen("detailScreen/{$KEY_ID_PEMESANAN}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}
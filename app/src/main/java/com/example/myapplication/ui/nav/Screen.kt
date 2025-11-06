package com.example.myapplication.ui.nav

sealed class Screen(val route: String) {
    data object Home        : Screen("home")
    data object ShopList    : Screen("shopList")
    data object ItemDetails : Screen("itemDetails")
    data object Favourites  : Screen("favourites")
    data object Settings    : Screen("settings")
    data object Profile     : Screen("profile")
}
package com.example.myapplication.ui.nav

sealed class Screen(val route: String) {
    data object ShopList : Screen("shopList")
    data object Login   : Screen("login")
    data object Register: Screen("register")
}
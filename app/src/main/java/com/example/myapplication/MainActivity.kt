package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.model.Product
import com.example.myapplication.ui.nav.PanelDest
import com.example.myapplication.ui.nav.Screen
import com.example.myapplication.ui.screens.FavoritesScreen
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.ItemDetailsScreen
import com.example.myapplication.ui.screens.ProfileScreen
import com.example.myapplication.ui.screens.SettingsScreen
import com.example.myapplication.ui.screens.ShopList
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val favourites = remember { mutableStateListOf<Product>() }

                fun goFromDrawer(dest: PanelDest) {
                    val route = when (dest) {
                        PanelDest.Home       -> Screen.Home.route
                        PanelDest.ShopList   -> Screen.ShopList.route
                        PanelDest.Favourites -> Screen.Favourites.route
                        PanelDest.Settings   -> Screen.Settings.route
                        PanelDest.Profile    -> Screen.Profile.route
                    }
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onPanelSelect = { dest -> goFromDrawer(dest) }
                        )
                    }

                    composable(Screen.ShopList.route) {
                        ShopList(
                            favouritesCount = favourites.size,
                            onAddFavourite = { product ->
                                if (favourites.none { it.id == product.id }) {
                                    favourites.add(product)
                                }
                            },
                            onPanelSelect = { dest -> goFromDrawer(dest) },
                            onBuy = {
                                navController.navigate(Screen.ItemDetails.route)
                            }
                        )
                    }

                    composable(Screen.ItemDetails.route) {
                        ItemDetailsScreen(
                            onMenuClick = { navController.popBackStack() }
                        )
                    }

                    composable(Screen.Favourites.route) {
                        FavoritesScreen(
                            favourites = favourites,
                            onPanelSelect = { dest -> goFromDrawer(dest) }
                        )
                    }

                    composable(Screen.Settings.route) {
                        SettingsScreen(
                            onPanelSelect = { dest -> goFromDrawer(dest) }
                        )
                    }

                    composable(Screen.Profile.route) {
                        ProfileScreen(
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
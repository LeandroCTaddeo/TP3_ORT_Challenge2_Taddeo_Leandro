package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.*
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                val nav = rememberNavController()

                val routes = mapOf(
                    PanelDest.ShopList   to "shopList",
                    PanelDest.Favourites to "favourites",
                    PanelDest.Settings   to "settings",
                    PanelDest.Profile    to "profile"
                )

                val favourites = remember { mutableStateListOf<Product>() }

                Scaffold(Modifier.fillMaxSize()) { inner ->
                    NavHost(
                        navController = nav,
                        startDestination = "home",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(inner)
                    ) {
                        composable("home") {
                            HomeScreen(
                                modifier = Modifier.fillMaxSize(),
                                onSelectFromPanel = { dest ->
                                    nav.navigate(routes.getValue(dest)) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("shopList") {
                            ShopList(
                                modifier = Modifier.fillMaxSize(),
                                favouritesCount = favourites.size,
                                onAddFavourite = { p ->
                                    if (favourites.none { it.id == p.id }) favourites += p
                                },
                                onPanelSelect = { dest ->
                                    nav.navigate(routes.getValue(dest)) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("favourites") {
                            FavouritesScreen(
                                items = favourites,
                                modifier = Modifier.fillMaxSize(),
                                onPanelSelect = { dest ->
                                    nav.navigate(routes.getValue(dest)) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("settings") {
                            SettingsScreen(
                                modifier = Modifier.fillMaxSize(),
                                onPanelSelect = { dest ->
                                    nav.navigate(routes.getValue(dest)) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("profile") {
                            ProfileScreen(
                                modifier = Modifier.fillMaxSize(),
                                onBack = { nav.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
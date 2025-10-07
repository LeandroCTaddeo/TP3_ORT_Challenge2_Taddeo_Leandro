@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class PanelDest { ShopList, Favourites, Profile, Settings }

@Composable
fun Panel(
    selected: PanelDest,
    favouritesCount: Int = 0,
    onSelect: (PanelDest) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier = modifier) {
        Spacer(Modifier.height(16.dp))

        NavigationDrawerItem(
            label = { Text("Shop list") },
            selected = selected == PanelDest.ShopList,
            onClick = { onSelect(PanelDest.ShopList) },
            icon = { Icon(Icons.Filled.Home, contentDescription = null) }
        )

        NavigationDrawerItem(
            label = { Text("Favourites") },
            selected = selected == PanelDest.Favourites,
            onClick = { onSelect(PanelDest.Favourites) },
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            badge = {
                if (favouritesCount > 0) {
                    BadgedBox(badge = { Badge { Text("$favouritesCount") } }) {}
                }
            }
        )

        NavigationDrawerItem(
            label = { Text("Profile") },
            selected = selected == PanelDest.Profile,
            onClick = { onSelect(PanelDest.Profile) },
            icon = { Icon(Icons.Filled.Person, contentDescription = null) }
        )

        NavigationDrawerItem(
            label = { Text("Settings") },
            selected = selected == PanelDest.Settings,
            onClick = { onSelect(PanelDest.Settings) },
            icon = { Icon(Icons.Filled.Settings, contentDescription = null) }
        )
    }
}
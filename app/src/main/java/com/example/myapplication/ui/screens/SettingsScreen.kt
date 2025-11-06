@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.AppDrawer
import com.example.myapplication.ui.components.TopBar
import com.example.myapplication.ui.nav.PanelDest
import kotlinx.coroutines.launch

private val ScreenBg = Color(0xFFF4EAE7)
private val LabelColor = Color(0xFFB7ACA7)
private val TextColor = Color(0xFF3D322F)
private val Accent = Color(0xFFA5522A)

@Composable
fun SettingsScreen(
    onPanelSelect: (PanelDest) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var push by remember { mutableStateOf(true) }
    var dark by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                selected = PanelDest.Settings,
                onSelect = { dest ->
                    scope.launch { drawerState.close() }
                    onPanelSelect(dest)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBg)
        ) {
            TopBar(
                title = "Settings",
                onMenuClick = { scope.launch { drawerState.open() } }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text("Account Settings", color = LabelColor)
                Spacer(Modifier.height(12.dp))

                SettingsRowText("Edit profile")
                SettingsRowText("Change password")

                SettingsRowSwitch(
                    label = "Push notifications",
                    checked = push,
                    onCheckedChange = { push = it }
                )

                SettingsRowSwitch(
                    label = "Dark mode",
                    checked = dark,
                    onCheckedChange = { dark = it }
                )

                Spacer(
                    Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFDFD5D1))
                        .height(1.dp)
                )

                Spacer(Modifier.height(16.dp))
                Text("More", color = LabelColor)
                Spacer(Modifier.height(12.dp))
                SettingsRowText("About us")
                SettingsRowText("Privacy policy")
                SettingsRowText("Terms and conditions")
            }
        }
    }
}

@Composable
private fun SettingsRowText(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, color = TextColor)
        Text(">", color = TextColor)
    }
}

@Composable
private fun SettingsRowSwitch(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = TextColor)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Accent,
                uncheckedThumbColor = Color.White
            )
        )
    }
}
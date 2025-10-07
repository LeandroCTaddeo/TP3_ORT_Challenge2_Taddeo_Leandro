@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.BottomBarRectangular
import com.example.myapplication.ui.components.MenuBars
import com.example.myapplication.ui.components.MenuButton
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onPanelSelect: (PanelDest) -> Unit = {}
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarHeight = 64.dp
    val bottomPadding = 96.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Panel(
                selected = PanelDest.Settings,
                favouritesCount = 0,
                onSelect = { dest ->
                    scope.launch { drawerState.close() }
                    onPanelSelect(dest)
                }
            )
        }
    ) {
        Box(modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topBarHeight + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = bottomPadding
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SectionHeader("Account Settings")
                SettingRowNav("Edit profile")
                SettingRowNav("Change password")
                SettingRowSwitch("Push notifications", checked = false)
                SettingRowSwitch("Dark mode", checked = false)

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                SectionHeader("More")
                SettingRowNav("About us")
                SettingRowNav("Privacy policy")
                SettingRowNav("Terms and conditions")
            }

            // Top bar
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(topBarHeight)
                    .background(Color.White)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(onClick = { scope.launch { drawerState.open() } }) { MenuBars() }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Settings",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.people),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp).clip(CircleShape)
                )
            }

            // Bottom bar
            BottomBarRectangular(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color(0xFFB9ADA7),
            fontWeight = FontWeight.SemiBold
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun SettingRowNav(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable { /* no-op */ }
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text("›", color = Color(0xFF8E8E8E))
    }
}

@Composable
private fun SettingRowSwitch(title: String, checked: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable { /* no-op */ }
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = { /* no-op */ },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF9A4A17),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFF9A4A17).copy(alpha = 0.5f)
            )
        )
    }
}
package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.BottomBarRectangular
import com.example.myapplication.ui.components.MenuBars
import com.example.myapplication.ui.components.MenuButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSelectFromPanel: (PanelDest) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarHeight = 64.dp
    val bottomPadding = 96.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Panel(
                selected = PanelDest.ShopList,
                favouritesCount = 0,
                onSelect = { dest ->
                    scope.launch { drawerState.close() }
                    onSelectFromPanel(dest)
                }
            )
        }
    ) {
        Box(modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(top = topBarHeight, bottom = bottomPadding)
            )

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
                    text = "TITLE",
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

            BottomBarRectangular(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
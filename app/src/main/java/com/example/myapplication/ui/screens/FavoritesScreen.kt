@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.*
import kotlinx.coroutines.launch

@Composable
fun FavouritesScreen(
    items: List<Product>,
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
                selected = PanelDest.Favourites,
                favouritesCount = items.size,
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
                        bottom = bottomPadding,
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                if (items.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) { Text("No favourites yet") }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(items) { p ->
                            FavouriteRow(p)
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                CenterBuyButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
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
                    text = "Favourites",
                    color = Color.Black,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
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
private fun FavouriteRow(p: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = p.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(p.title, style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                Text(p.price, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF6B6B6B))
            }
            Spacer(Modifier.width(12.dp))
            SecondaryButton(
                text = "Buy",
                onClick = { },
                containerColor = DarkOrange,
                contentColor = Color.White
            )
        }
    }
}
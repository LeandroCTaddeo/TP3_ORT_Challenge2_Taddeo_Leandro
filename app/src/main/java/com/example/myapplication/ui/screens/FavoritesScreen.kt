@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.AppDrawer
import com.example.myapplication.ui.components.BottomBarRectangular
import com.example.myapplication.ui.components.TopBar
import com.example.myapplication.ui.components.topBarHeight
import com.example.myapplication.ui.model.Product
import com.example.myapplication.ui.nav.PanelDest
import kotlinx.coroutines.launch

private val ScreenBg = Color(0xFFF4EAE7)
private val Accent = Color(0xFFA5522A)

@Composable
fun FavoritesScreen(
    favourites: List<Product>,
    onPanelSelect: (PanelDest) -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                selected = PanelDest.Favourites,
                favouritesCount = favourites.size,
                onSelect = { dest ->
                    scope.launch { drawerState.close() }
                    onPanelSelect(dest)
                }
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(ScreenBg)
        ) {
            TopBar(
                title = "Favourites",
                onMenuClick = { scope.launch { drawerState.open() } },
                modifier = Modifier.align(Alignment.TopCenter)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topBarHeight() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 110.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(favourites) { index, p ->
                    FavouriteItemCard(index = index + 1, product = p)
                }
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 72.dp),
                color = Accent,
                shape = RoundedCornerShape(999.dp),
                shadowElevation = 6.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "+", color = Color.White)
                    Spacer(Modifier.width(6.dp))
                    Text(text = "Buy", color = Color.White)
                }
            }

            BottomBarRectangular(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun FavouriteItemCard(
    index: Int,
    product: Product
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFA5522A)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = index.toString(), color = Color.White)
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = product.title, color = Color(0xFF4A3B35))
                Text(text = product.price, color = Color(0xFF8D7A72))
            }

            Spacer(Modifier.width(12.dp))

            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}
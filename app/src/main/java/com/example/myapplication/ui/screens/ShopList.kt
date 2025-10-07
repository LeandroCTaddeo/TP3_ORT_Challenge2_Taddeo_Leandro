@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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

data class Product(val id: Int, val title: String, val price: String, val imageRes: Int)

@Composable
fun ShopList(
    modifier: Modifier = Modifier,
    favouritesCount: Int = 0,
    onAddFavourite: (Product) -> Unit = {},
    onPanelSelect: (PanelDest) -> Unit = {}
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarHeight = 64.dp
    val bottomPadding = 96.dp

    val products = remember {
        listOf(
            Product(1, "Leather boots", "27,5 $", R.drawable.botas),
            Product(2, "Leather boots", "27,5 $", R.drawable.botas),
            Product(3, "Leather boots", "27,5 $", R.drawable.botas)
        )
    }
    var showDetail by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Panel(
                selected = PanelDest.ShopList,
                favouritesCount = favouritesCount,
                onSelect = { dest ->
                    scope.launch { drawerState.close() }
                    onPanelSelect(dest)
                }
            )
        }
    ) {
        Box(modifier.fillMaxSize()) {

            if (!showDetail) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = topBarHeight + 16.dp,
                        bottom = bottomPadding
                    ),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(products) { p ->
                        ProductCard(
                            product = p,
                            onFav = { onAddFavourite(p) },
                            onBuy = { showDetail = true }
                        )
                    }
                }
            } else {
                ProductDetail(
                    onBack = { showDetail = false },
                    onBuy = { }
                )
            }

            // Top bar
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(topBarHeight)
                    .background(Color.White)
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(onClick = { scope.launch { drawerState.open() } }) { MenuBars() }
                Spacer(Modifier.width(12.dp))
                Text(
                    text = if (showDetail) "Leather boots" else "Shop List",
                    color = Color.Black,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
                Image(
                    painter = painterResource(id = R.drawable.people),
                    contentDescription = "PeopleProfile",
                    modifier = Modifier.size(40.dp).clip(CircleShape)
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
private fun ProductCard(
    product: Product,
    onFav: () -> Unit,
    onBuy: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(
            text = product.title,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 12.dp)
        )
        Text(
            text = product.price,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "Great warm shoes from the artificial leather. You",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "can buy this model only in our shop",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(text = "Add to favourite", onClick = onFav)
            Spacer(Modifier.width(12.dp))
            SecondaryButton(
                text = "Buy",
                onClick = onBuy,
                containerColor = DarkOrange,
                contentColor = Color.White
            )
        }
    }
}
@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.AppDrawer
import com.example.myapplication.ui.components.BottomBarRectangular
import com.example.myapplication.ui.components.SecondaryButton
import com.example.myapplication.ui.components.TopBar
import com.example.myapplication.ui.components.topBarHeight
import com.example.myapplication.ui.model.Product
import com.example.myapplication.ui.model.sampleProducts
import com.example.myapplication.ui.nav.PanelDest
import kotlinx.coroutines.launch

private val ScreenBg = Color(0xFFF4EAE7)
private val CardBg = Color.White
private val Accent = Color(0xFFA5522A)
private val TextMuted = Color(0xFF907A71)
private val TextPrimary = Color(0xFF4A3B35)

@Composable
fun ShopList(
    modifier: Modifier = Modifier,
    favouritesCount: Int = 0,
    onAddFavourite: (Product) -> Unit = {},
    onPanelSelect: (PanelDest) -> Unit = {},
    onBuy: (Product) -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                selected = PanelDest.ShopList,
                favouritesCount = favouritesCount,
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
                title = "Shop list",
                onMenuClick = { scope.launch { drawerState.open() } },
                modifier = Modifier.align(Alignment.TopCenter)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topBarHeight() + 16.dp,
                        bottom = 110.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleProducts) { product ->
                    ProductLargeCard(
                        product = product,
                        onFav = { onAddFavourite(product) },
                        onBuy = { onBuy(product) }
                    )
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
private fun ProductLargeCard(
    product: Product,
    onFav: () -> Unit,
    onBuy: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 6.dp,
        color = CardBg,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = product.title, color = TextPrimary)
                Text(text = product.price, color = TextPrimary)
                Text(
                    text = "Great warm shoes from the artificial leather. You can buy this model only in our shop",
                    color = TextMuted,
                    modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SecondaryButton(
                        text = "Add to favourite",
                        onClick = onFav,
                        containerColor = Color.White,
                        contentColor = Accent,
                        borderColor = Accent,
                        cornerRadius = 30.dp
                    )
                    Button(
                        onClick = onBuy,
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Buy")
                    }
                }
            }
        }
    }
}
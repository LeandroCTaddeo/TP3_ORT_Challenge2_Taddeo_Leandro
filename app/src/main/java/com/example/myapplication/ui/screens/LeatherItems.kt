@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.BottomBarRectangular
import com.example.myapplication.ui.components.DarkOrange
import com.example.myapplication.ui.components.MenuBars
import com.example.myapplication.ui.components.MenuButton
import com.example.myapplication.ui.components.PrimaryButton
import com.example.myapplication.ui.components.SecondaryButton
import kotlinx.coroutines.launch

@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onBuy: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topBarHeight = 64.dp
    val bottomPadding = 96.dp

    var expanded by remember { mutableStateOf(false) }
    val sizes = listOf("39", "40", "41", "42", "43", "44")
    var selectedSize by remember { mutableStateOf<String?>(null) }
    var count by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Panel(
                selected = PanelDest.ShopList,
                favouritesCount = 3,
                onSelect = { _ -> scope.launch { drawerState.close() } }
            )
        }
    ) {
        Box(modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topBarHeight + 24.dp,
                        start = 24.dp,
                        end = 24.dp,
                        bottom = bottomPadding
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Select size", style = MaterialTheme.typography.titleLarge)

                Spacer(Modifier.height(16.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedSize ?: "",
                        onValueChange = {},
                        label = { Text("Input") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        sizes.forEach { s ->
                            DropdownMenuItem(
                                text = { Text(s) },
                                onClick = {
                                    selectedSize = s
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(28.dp))

                Text("Count of product", style = MaterialTheme.typography.titleLarge)

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = count,
                    onValueChange = { count = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Input") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryButton(
                        text = "Back",
                        onClick = onBack,
                        containerColor = Color.White,
                        contentColor = DarkOrange,
                        borderColor = DarkOrange,
                        modifier = Modifier
                            .height(44.dp)
                            .width(120.dp)
                    )
                    SecondaryButton(
                        text = "Buy",
                        onClick = onBuy,
                        containerColor = DarkOrange,
                        contentColor = Color.White,
                        modifier = Modifier
                            .height(44.dp)
                            .width(120.dp)
                    )
                }
            }

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
                    text = "Leather boots",
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
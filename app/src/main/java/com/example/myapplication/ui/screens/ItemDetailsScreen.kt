@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.TopBar
import com.example.myapplication.ui.components.topBarHeight

private val ScreenBg = Color(0xFFF4EAE7)
private val Accent = Color(0xFFA5522A)
private val TextPrimary = Color(0xFF4A3B35)

@Composable
fun ItemDetailsScreen(
    onMenuClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedSize by remember { mutableStateOf("Input") }
    var count by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
    ) {
        TopBar(
            title = "Leather boots",
            onMenuClick = onMenuClick,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topBarHeight() + 16.dp, start = 16.dp, end = 16.dp, bottom = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Select size", color = TextPrimary)
            Spacer(Modifier.height(8.dp))

            Box {
                OutlinedTextField(
                    value = selectedSize,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Label") },
                    readOnly = true
                )
                Spacer(
                    Modifier
                        .matchParentSize()
                        .clickable { expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    listOf("36", "38", "40", "42").forEach { size ->
                        DropdownMenuItem(
                            text = { Text(size) },
                            onClick = {
                                selectedSize = size
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
            Text(text = "Count of producy", color = TextPrimary)
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = count,
                onValueChange = { count = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Label") }
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                shape = androidx.compose.foundation.shape.RoundedCornerShape(999.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Accent),
                color = Color.Transparent,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Back", color = Accent)
                }
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(999.dp),
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
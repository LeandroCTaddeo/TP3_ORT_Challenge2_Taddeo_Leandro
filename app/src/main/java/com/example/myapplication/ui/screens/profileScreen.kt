@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.BottomBarRectangular
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    val topBarHeight = 64.dp
    val bottomPadding = 96.dp
    val scope = rememberCoroutineScope()

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(id = R.drawable.people),
                    contentDescription = null,
                    modifier = Modifier
                        .size(112.dp)
                        .clip(CircleShape)
                )
                Button(
                    onClick = { /* no-op */ },
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBF5700),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(28.dp)
                        .offset(x = 6.dp, y = 6.dp)
                ) {
                    // sin icono/emoji
                    Spacer(Modifier.size(1.dp))
                }
            }

            Spacer(Modifier.height(12.dp))
            Text("Martin", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            Text("UI UX DESIGN", color = Color(0xFF9A8E88))

            Spacer(Modifier.height(24.dp))

            LabeledReadOnlyField(label = "E-Mail Address", value = "xxx@gmail.com")
            Spacer(Modifier.height(12.dp))

            LabeledReadOnlyField(label = "Phone Number", value = "+5493123135")
            Spacer(Modifier.height(12.dp))

            LabeledReadOnlyField(label = "Web Site", value = "www.google.com")
            Spacer(Modifier.height(12.dp))

            LabeledReadOnlyField(label = "Password", value = "xxxxxxxxxxxxxx")
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
            TextButton(onClick = onBack) {
                Text("Back", color = Color.Black, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.width(8.dp))
            Text(
                text = "Profile",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Image(
                painter = painterResource(id = R.drawable.people),
                contentDescription = null,
                modifier = Modifier.size(28.dp).clip(CircleShape)
            )
        }

        BottomBarRectangular(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun LabeledReadOnlyField(
    label: String,
    value: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFF9A8E88))
        )
        OutlinedTextField(
            value = value,
            onValueChange = { },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        )
    }
}
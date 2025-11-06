package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.TopBarBack

private val ScreenBg = Color(0xFFF4EAE7)

@Composable
fun ProfileScreen(
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBg)
    ) {
        TopBarBack(
            title = "Profile",
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(120.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.people),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFA5522A),
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
            Text("Martin")
            Text("UI UX DESIGN", color = Color(0xFF9C9089))

            Spacer(Modifier.height(24.dp))
            ProfileField(label = "E-Mail Address", value = "xxx@gmail.com")
            Spacer(Modifier.height(12.dp))
            ProfileField(label = "Phone Number", value = "+5493123135")
            Spacer(Modifier.height(12.dp))
            ProfileField(label = "Web Site", value = "www.google.com")
            Spacer(Modifier.height(12.dp))
            ProfileField(label = "Password", value = "xxxxxxxxxxxx")
        }
    }
}

@Composable
private fun ProfileField(
    label: String,
    value: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, color = Color(0xFFB7ACA7))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(value, modifier = Modifier.weight(1f))
        }
    }
}
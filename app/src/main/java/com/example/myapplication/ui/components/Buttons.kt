package com.example.myapplication.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

// Paleta
val DarkOrange = Color(0xFFBF5700)

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = DarkOrange,
    contentColor: Color = DarkOrange,
    containerColor: Color = Color.White,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(borderWidth, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = DarkOrange,
    contentColor: Color = Color.White,
    containerColor: Color = DarkOrange,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(borderWidth, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text)
    }
}

@Composable
fun MenuButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .width(56.dp)
        .height(46.dp),
    cornerRadius: Dp = 12.dp,
    containerColor: Color = Color.White,
    contentColor: Color = Color.Gray,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        content()
    }
}

@Composable
fun MenuBars(
    modifier: Modifier = Modifier,
    barWidth: Dp = 22.dp,
    barHeight: Dp = 2.dp,
    gap: Dp = 3.dp,
    color: Color = Color.Gray
) {
    Column(
        modifier = modifier.width(barWidth),
        verticalArrangement = Arrangement.spacedBy(gap),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(barHeight)
                    .clip(RoundedCornerShape(1.dp))
                    .background(color)
            )
        }
    }
}


@Composable
fun BottomBarRectangular(
    modifier: Modifier = Modifier.fillMaxWidth(),
    onClick1: () -> Unit = {},
    onClick2: () -> Unit = {},
    onClickCenter: () -> Unit = {},
    onClick4: () -> Unit = {},
    onClick5: () -> Unit = {}
) {
    val barHeight = 72.dp
    val centerSize = 60.dp
    val centerOverlap = 12.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(barHeight + centerOverlap)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .align(Alignment.BottomCenter),
            color = Color.White,
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavImage(R.drawable.boton1, onClick1)
                Spacer(Modifier.width(16.dp))
                NavImage(R.drawable.boton2, onClick2)

                Spacer(Modifier.weight(1f))

                NavImage(R.drawable.boton4, onClick4)
                Spacer(Modifier.width(16.dp))
                NavImage(R.drawable.boton5, onClick5)
            }
        }

        Button(
            onClick = onClickCenter,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkOrange,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(centerSize)
                .align(Alignment.BottomCenter)
                .offset(y = -centerOverlap)
        ) {
            Image(
                painter = painterResource(id = R.drawable.boton3),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun NavImage(
    @DrawableRes resId: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clickable { onClick() }
    )
}

@Composable
fun CenterBuyButton(
    modifier: Modifier = Modifier,
    text: String = "Buy",
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(44.dp)
            .widthIn(min = 120.dp),
        shape = RoundedCornerShape(22.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkOrange,
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Text("+", color = Color.White)
        }
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}


@Composable
fun LinkTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(text = text, color = Color.Blue, fontSize = 20.sp)
    }
}
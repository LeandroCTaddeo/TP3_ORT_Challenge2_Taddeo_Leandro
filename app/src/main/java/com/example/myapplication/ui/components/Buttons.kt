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
import com.example.myapplication.R

val DarkOrange = Color(0xFFBF5700)

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(text)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentColor: Color = DarkOrange,
    borderColor: Color = DarkOrange,
    cornerRadius: Dp = 12.dp
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(text)
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

        Box(
            modifier = Modifier
                .size(centerSize)
                .align(Alignment.TopCenter)
                .clip(CircleShape)
                .background(DarkOrange)
                .clickable { onClickCenter() },
            contentAlignment = Alignment.Center
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
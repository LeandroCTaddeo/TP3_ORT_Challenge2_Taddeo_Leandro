package com.example.myapplication.ui.model

import androidx.annotation.DrawableRes
import com.example.myapplication.R

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    @DrawableRes val imageRes: Int
)

val sampleProducts = listOf(
    Product(1, "Leather boots", "27,5 $", R.drawable.botas),
    Product(2, "Leather boots", "27,5 $", R.drawable.botas),
    Product(3, "Leather boots", "27,5 $", R.drawable.botas),
    Product(4, "Leather boots", "27,5 $", R.drawable.botas)
)
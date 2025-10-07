package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun LoginScreen(modifier: Modifier = Modifier,
                onBack: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login here", fontSize = 37.sp, color = Color.Blue)
        Text("Welcome back you've", fontSize = 26.sp, color = Color.Black)
        Text("been missed!", fontSize = 26.sp, color = Color.Black)
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Password") }, visualTransformation = PasswordVisualTransformation()
        )
        TextButton(
            onClick = { },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = "Forgot your password?",
                color = Color.Blue,
                fontSize = 14.sp
            )
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 330.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .width(160.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(12.dp)
        ) { Text("Sign in") }
        Spacer(Modifier.height(12.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .width(160.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Gray,
            ),
            shape = RoundedCornerShape(12.dp)
        ) { Text("Create new account") }

        Spacer(Modifier.height(8.dp))
        Text(
            "Or continue with",
            color = Color.Blue,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(Modifier.fillMaxSize())
}
package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

// Composable function to create the Home screen
@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image composable to display a shopping cart image
       Image(painter = painterResource(id = R.drawable.download), contentDescription ="Shopping Cart" )
        // Button to navigate to the Vegetable screen when clicked
        Button(onClick = { navController.navigate(Screen.VegetableScreen.route)}) {
            Text(text = "Start Shopping")
        }
    }
}

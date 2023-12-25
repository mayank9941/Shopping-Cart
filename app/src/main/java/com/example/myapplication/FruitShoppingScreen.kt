package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
// Composable function to create the Fruit Shopping screen
@Composable
fun FruitShoppingScreen(navController: NavController // Navigation controller for navigating between screens
                        ,setFruitList: (List<String>)->Unit // Function to set the fruit list externally
                        ,fruitList:List<String> // List of fruits for the shopping screen
)
{
    // Scaffold is a Material Design component providing basic layout structure
    Scaffold(
        topBar = {
            // TopAppBar is the top bar of the screen
            TopAppBar(
                // Setting colors for the app bar
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                // Title of the app bar
                title = {
                    Text("Fruits")
                },
                // Navigation icon to go back
                navigationIcon = {
                    IconButton(onClick = {
                        // Navigate back to the Vegetable screen
                        navController.navigate(Screen.VegetableScreen.route)

                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    } }
            )
        }
    ) { innerPadding ->
        // Column is a vertical arrangement of UI elements
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Row for displaying the column headers "Items" and "Price/Kg"
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 20.dp)){
                Text(text = "Items", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 145.dp))
                Text(text = "Price/Kg", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            // FruitsList is a custom composable function to display the list of fruits
            FruitsList(fruitList,setFruitList)
            // Row for buttons (Cancel and Next)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f, false),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Bottom
            ){
                // OutlinedButton for Cancel action
                OutlinedButton(modifier = Modifier.weight(1f), onClick ={
                    // Clear the fruit list and navigate to the HomeScreen
                    setFruitList.invoke(listOf("clear"))
                    navController.navigate(Screen.HomeScreen.route)

                } ) {
                    Text("Cancel")
                }
                // Button for Next action, navigating to the Billing screen
                Button(
                    modifier = Modifier.weight(1f),

                    onClick = {

                        navController.navigate(Screen.BillingScreen.route)
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}

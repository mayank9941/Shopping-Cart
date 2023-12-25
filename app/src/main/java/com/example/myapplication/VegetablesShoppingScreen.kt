package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions

@OptIn(ExperimentalMaterial3Api::class)
// Composable function for the Vegetable Shopping screen
@Composable
fun VegetableShoppingScreen(navController: NavController // Navigation controller for navigating between screens
                            ,setVegList:(List<String>)->Unit,  // Function to set the vegetable list externally
                            vegList:List<String>) // List of selected vegetables
{
    // Log the elements in the vegetable list
    vegList.forEachIndexed { index, element ->
        Log.d("MainActivity", "Element at index $index: $element")
    }
    // Scaffold is a Material Design component providing basic layout structure
    Scaffold(
        topBar = {
            // TopAppBar is the top bar of the screen
            TopAppBar(
                // Setting colors for the app bar
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                // Title of the app bar
                title = {
                    Text("Vegetables")
                },
                // Navigation icon to go back
                navigationIcon = {
                    IconButton(onClick =
                    // Navigate back to the Home screen
                    {
                        navController.navigate(Screen.HomeScreen.route)

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
                Text(text = "Items", fontSize = 20.sp, fontWeight = FontWeight.Bold
                ,modifier=Modifier.padding(start = 145.dp))
                Text(text = "Price/Kg", fontSize = 20.sp, fontWeight = FontWeight.Bold
                  )
            }
            // VegetablesList is a custom composable function to display the list of vegetables
            VegetablesList(setVegList,vegList)
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
                    // Clear the vegetable list and navigate to the HomeScreen
                    setVegList.invoke(listOf("clear"))

                    navController.navigate(Screen.HomeScreen.route)

                } ) {
                    Text("Cancel")
                }
                // Button for Next action, navigating to the FruitScreen
                Button(
                    modifier = Modifier.weight(1f),

                    onClick =
                    {
                        navController.navigate(Screen.FruitScreen.route)

                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}



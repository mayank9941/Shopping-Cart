@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillScreen(navController: NavController,vegList: List<String>,fruitList:List<String>,setFruitList: (List<String>)->Unit,setVegList: (List<String>)->Unit) {
    // Scaffold is a basic layout structure for Material Design components
    Scaffold(
        topBar = {
            // TopAppBar is the top app bar in the layout
            TopAppBar(
                // Setting colors for the app bar
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                // Title of the app bar
                title = {
                    Text("Bill")
                },
                // Navigation icon to go back
                navigationIcon = {
                    IconButton(onClick = {
                        // Navigate back when the back arrow is clicked
                        navController.navigate(Screen.FruitScreen.route)
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    } }
            )
        }
    ) { innerPadding ->
        // Column is a vertical arrangement of element
        Column(

            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // Row for displaying the title "Items Summary"
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 20.dp)){
                Text(text = "Items Summary", fontSize = 40.sp, fontWeight = FontWeight.Bold)

            }
            // LazyColumn for displaying the list of vegetables selected
           LazyColumn(modifier=Modifier.padding(start=20.dp,end=20.dp))
           {
               items(vegList)
               {item->
                   Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                       val parts=item.split(',')
                       Text(text=parts[0],fontSize = 20.sp)
                       Text(text = parts[1],fontSize = 20.sp)
                   }


               }

           }
            // LazyColumn for displaying the list of fruits selected
            LazyColumn(modifier=Modifier.padding(start=20.dp,end=20.dp))
            {
                items(fruitList)
                {item->
                    Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        val parts=item.split(',')
                        Text(text=parts[0],fontSize = 20.sp)
                        Text(text = parts[1],fontSize = 20.sp)
                    }


                }

            }
            // Divider to separate the lists from the total
Divider(modifier=Modifier.fillMaxWidth())
            // Row to display the total amount
 Row(modifier= Modifier
     .fillMaxWidth()
     .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween)
 {

     Text(text="TOTAL :",fontSize = 20.sp)
     // If both lists are empty, display "RS 0"
     if(vegList.isEmpty()&&fruitList.isEmpty()){
     Text(text="RS 0",fontSize = 20.sp)}
     else
     {
         // Calculate and display the total amount
         var total:Int=0
         fruitList.forEach{  s ->
             val priceRS=s.split(',')[1]
             val priceInt=priceRS.substring(3).toInt()
             total+=priceInt
         }
         vegList.forEach {  s ->
             val priceRS=s.split(',')[1]
             val priceInt=priceRS.substring(3).toInt()
             total+=priceInt
         }
         Text(text="RS ${total}",fontSize = 20.sp)
     }

 }
            // Row for buttons (Cancel and Submit)
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
                    // Clear lists and navigate to the HomeScreen
                    setVegList.invoke(listOf("clear"))
                    setFruitList.invoke(listOf("clear"))

                    navController.navigate(Screen.HomeScreen.route)

                } ) {
                    Text("Cancel")
                }
                // Button for Submit action
                Button(
                    modifier = Modifier.weight(1f),

                    onClick = {
                        // Clear lists and navigate to the HomeScreen
                        setVegList.invoke(listOf("clear"))
                        setFruitList.invoke(listOf("clear"))
navController.navigate(Screen.HomeScreen.route)
                    }
                ) {
                    Text("Submit")
                }
            }
        }
    }
}

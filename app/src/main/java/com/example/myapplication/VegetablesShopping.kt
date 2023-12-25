@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Vegetable as Vegetable1

// Data class representing a Vegetable with name and price
data class Vegetable(val name:String,val price:String)

// List of predefined vegetables with their names and prices
val Vegetables = listOf<Vegetable1>(

    Vegetable1(name = "Tomato",price="RS 25"),
    Vegetable1(name ="Potato", price = "RS 40"),
    Vegetable1(name = "Capsicum", price = "RS 41"),
    Vegetable1(name="Carrot", price = "RS 50"),
    Vegetable1(name="Onion", price = "RS 62"),
    Vegetable1(name="Brinjal",price="RS 53"),
    Vegetable1(name = "Green Peas", price = "RS 71"),
    Vegetable1(name = "Cabbage", price = "RS 37")

)
// Composable function to display a list of vegetables
@Composable
fun VegetablesList(setVegList: (List<String>) -> Unit,vegList:List<String>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)

    ) {
        // Displaying each vegetable in the list using the 'vegetable' composable function
        items(Vegetables) { vegetable ->
            vegetable(vegList,name = vegetable.name, price = vegetable.price) { checked ->
                // When the checkbox state changes, update the vegetable list
                    setVegList.invoke(listOf(vegetable.name+","+vegetable.price)) // Add the vegetable name to the list


            }
        }
    }
}



@Composable
fun vegetable(vegList: List<String>, name: String, price: String, onCheckedChange: (Boolean) -> Unit){
    val checkedState = remember { mutableStateOf(vegList.contains(name+','+price)) }
    // Row is a composable that places its children in a horizontal row
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)

            ) {
        // Checkbox composable for selecting the vegetable
        Checkbox(checked =checkedState.value
            , onCheckedChange =
            // Update the checked state and invoke the callback
            {checkedState.value=it
                onCheckedChange.invoke(it)
                               },
            colors = CheckboxDefaults.colors(Color.Blue)
        )
        // Displaying the name and price of the vegetable
        Text(text = name, fontSize = 20.sp)
        Text(text = price, fontSize = 20.sp)
    }
}


















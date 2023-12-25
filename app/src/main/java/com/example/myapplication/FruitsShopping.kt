package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class representing a Fruit with name and price
data class Fruit(val name:String,val price:String)
// List of predefined fruits with their names and prices
val Fruits = listOf<Fruit>(

    Fruit(name = "Mango",price="RS 73"),
    Fruit(name ="Orange", price = "RS 40"),
    Fruit(name = "Banana", price = "RS 43"),
    Fruit(name="Papaya", price = "RS 95"),
    Fruit(name="Apple", price = "RS 89"),
    Fruit(name="Pineapple",price="RS 53"),
    Fruit(name = "Grapes", price = "RS 97"),
    Fruit(name = "Avocado", price = "RS 83")

)


// Composable function to display a list of fruits
@Composable
fun FruitsList(fruitList: List<String>,setFruitList:(List<String>)->Unit ) {
    // Composable function to display a list of fruits
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)

    ) {
        // Displaying each fruit in the list using the 'fruit' composable function
        items(Fruits) {Fruit -> fruit(fruitList,name = Fruit.name, price =Fruit.price){checked->
            // When the checkbox state changes, update the fruit list
            setFruitList.invoke(listOf(Fruit.name+","+Fruit.price))}
        }
    }
}
// Composable function to display a single fruit item with a checkbox
@Composable
fun fruit( fruitList:List<String>,name: String, price: String,onCheckedChange:(Boolean)->Unit){
    // Row is a composable that places its children in a horizontal row
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }) {
        // Mutable state to track the checked state of the checkbox
        val Checkedstate= remember { mutableStateOf(fruitList.contains(name+','+price)) }
        // Checkbox composable for selecting the fruit
        Checkbox(checked =Checkedstate.value
            , onCheckedChange ={
                // Update the checked state and invoke the callback
                Checkedstate.value=it
                onCheckedChange.invoke(it)
            },
            colors = CheckboxDefaults.colors(Color.Blue)
        )
        // Displaying the name and price of the fruit
        Text(text = name, fontSize = 20.sp)
        Text(text = price, fontSize = 20.sp)
    }
}
package com.example.myapplication

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
// Data class representing a pair of a String and an Int
data class StringIntPair(val stringValue: String, val intValue: Int)
// Sealed class for navigation screen arguments
sealed class ScreenArguments {
    object Empty : ScreenArguments()
    data class Screen2Args(val pairList: List<StringIntPair>) : ScreenArguments()
}



@Composable
fun Navigation()
{
    // Mutable state lists to track selected items for fruits and vegetables
    var fruitList = remember { mutableStateListOf<String>() }
    // Function to update the fruit list based on selected items
    val setFruitList: (List<String>) -> Unit = { items ->
        items.forEach { item ->
            // Clear the fruit list
            if(item=="clear")
            {
                fruitList.clear()
                return@forEach
            }
            if (fruitList.contains(item)) {
                fruitList.remove(item)
                Log.d("MainActivity", "Item removed: $item")
            } else {

                fruitList.add(item)
                Log.d("MainActivity", "Item added: $item")
            }
        }
        // Log the elements in the fruit list
        fruitList.forEachIndexed { index, element ->
            Log.d("MainActivity", "Element at index $index: $element")
        }
    }
    var vegList = remember { mutableStateListOf<String>() }
    // Function to update the vegetable list based on selected items
    val setVegList: (List<String>) -> Unit = { items ->
        items.forEach { item ->
            // Clear the vegetable list
            if(item=="clear")
            {
                vegList.clear()
                return@forEach
            }
            if (vegList.contains(item)) {
                vegList.remove(item)
                Log.d("MainActivity", "Item removed: $item")
            } else {

                vegList.add(item)
                Log.d("MainActivity", "Item added: $item")
            }
        }
        // Log the elements in the vegetable list
        vegList.forEachIndexed { index, element ->

            Log.d("MainActivity", "Element at index $index: $element")
        }
    }
    // Navigation controller to handle navigation between screens
    var navController= rememberNavController()
    // NavHost composable to define the navigation graph
    NavHost(navController = navController, startDestination =Screen.HomeScreen.route  )
    {
        // Composable for the Home screen
        composable(route=Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        // Composable for the Vegetable Shopping screen
        composable(route=Screen.VegetableScreen.route){

VegetableShoppingScreen(navController,setVegList,vegList)
        }
        // Composable for the Fruit Shopping screen
        composable(route=Screen.FruitScreen.route

            ){backStackEntry ->

            FruitShoppingScreen(navController, setFruitList,fruitList)
        }
        // Composable for the Billing screen
        composable(route=Screen.BillingScreen.route

        ){
            BillScreen(navController,vegList,fruitList,setFruitList,setVegList)
        }

    }

}
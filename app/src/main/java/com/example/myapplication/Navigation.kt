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

data class StringIntPair(val stringValue: String, val intValue: Int)

sealed class ScreenArguments {
    object Empty : ScreenArguments()
    data class Screen2Args(val pairList: List<StringIntPair>) : ScreenArguments()
}



@Composable
fun Navigation()
{

    var fruitList = remember { mutableStateListOf<String>() }
    val setFruitList: (List<String>) -> Unit = { items ->
        items.forEach { item ->
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
        fruitList.forEachIndexed { index, element ->
            Log.d("MainActivity", "Element at index $index: $element")
        }
    }
    var vegList = remember { mutableStateListOf<String>() }
    val setVegList: (List<String>) -> Unit = { items ->
        items.forEach { item ->
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
        vegList.forEachIndexed { index, element ->
            Log.d("MainActivity", "Element at index $index: $element")
        }
    }
    var navController= rememberNavController()
    NavHost(navController = navController, startDestination =Screen.HomeScreen.route  )
    {
        composable(route=Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route=Screen.VegetableScreen.route){

VegetableShoppingScreen(navController,setVegList,vegList)
        }
        composable(route=Screen.FruitScreen.route

            ){backStackEntry ->

            FruitShoppingScreen(navController, setFruitList,fruitList)
        }
        composable(route=Screen.BillingScreen.route

        ){
            BillScreen(navController,vegList,fruitList,setFruitList,setVegList)
        }

    }

}
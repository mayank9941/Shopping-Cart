package com.example.myapplication

sealed class Screen(val route:String) {
    object HomeScreen:Screen("home_screen")

    object VegetableScreen:Screen("vegetable_screen")
    object FruitScreen:Screen("fruit_screen")
    object BillingScreen:Screen("billing_screen")


}
package com.example.myapplication

// Sealed class representing different screens in the navigation graph
sealed class Screen(val route:String) {
    // Object representing the Home screen
    object HomeScreen:Screen("home_screen")
    // Object representing the Vegetable Shopping screen
    object VegetableScreen:Screen("vegetable_screen")
    // Object representing the Fruit Shopping screen
    object FruitScreen:Screen("fruit_screen")
    // Object representing the Billing screen
    object BillingScreen:Screen("billing_screen")


}
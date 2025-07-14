package com.kareem.littlelemon.util

object AppConstants {
    
    // API URLs
    object Api {
        const val MENU_URL = "https://raw.githubusercontent.com/KareemElnagar/Working-With-Data-API/main/menu.json"
    }
    
    // Database
    object Database {
        const val DATABASE_NAME = "little_lemon_database"
        const val DATABASE_VERSION = 1
    }
    
    // Categories
    object Categories {
        const val ALL = "All"
        const val STARTERS = "Starters"
        const val MAINS = "Mains"
        const val DESSERT = "Dessert"
        const val DRINKS = "Drinks"
        
        val ALL_CATEGORIES = listOf(ALL, STARTERS, MAINS, DESSERT, DRINKS)
    }
    
    // UI Constants
    object UI {
        const val CARD_ELEVATION = 8
        const val IMAGE_SIZE = 100
        const val CART_IMAGE_SIZE = 80
        const val PROFILE_IMAGE_SIZE = 50
        const val LOGO_SIZE = 130
    }
    
    // Shared Preferences
    object SharedPrefs {
        const val USER_KEY = "UserDetails"
        const val FIRST_NAME_KEY = "FirstName"
        const val LAST_NAME_KEY = "LastName"
        const val EMAIL_KEY = "Email"
        const val REGISTER_KEY = "UserRegistered"
    }
    
    // Navigation
    object Navigation {
        const val DISH_DETAILS_ROUTE = "DishDetails"
        const val ORDERS = "Orders"
        const val MENU = "Menu"
        const val ONBOARDING = "Onboarding"
        const val PROFILE = "Profile"
        const val HOME = "Home"
    }
} 
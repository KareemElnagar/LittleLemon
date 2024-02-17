package com.kareem.littlelemon.util

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}

object Onboarding : Destinations {
    override val route = "Onboarding"
}
object MenuScreen : Destinations {
    override val route = "Menu"
}
object Orders : Destinations {
    override val route = "Orders"
}
object DishDetails : Destinations {
    override val route = "DishDetails"
}
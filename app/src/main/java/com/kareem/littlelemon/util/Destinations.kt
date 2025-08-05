package com.kareem.littlelemon.util

interface Destinations {
    val route: String
}

object Home : Destinations {
//    override val route = "Home"
    override val route = AppConstants.Navigation.HOME
}

object Profile : Destinations {
    override val route = AppConstants.Navigation.PROFILE
}
object Splash : Destinations {
    override val route = "splash"
}
object Onboarding : Destinations {
    override val route = AppConstants.Navigation.ONBOARDING
}
object MenuScreen : Destinations {
    override val route = AppConstants.Navigation.MENU
}
object Orders : Destinations {
    override val route = AppConstants.Navigation.ORDERS
}
object DishDetails : Destinations {
    override val route = AppConstants.Navigation.DISH_DETAILS_ROUTE
}
object Cart : Destinations {
    override val route = AppConstants.Navigation.CART
}
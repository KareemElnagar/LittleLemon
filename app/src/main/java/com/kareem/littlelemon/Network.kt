package com.kareem.littlelemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: Double,
    val category: String,
    val image: String,
    val description: String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        price,
        category,
        image,
        description
    )
}

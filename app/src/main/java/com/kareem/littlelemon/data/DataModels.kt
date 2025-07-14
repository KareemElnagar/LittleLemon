package com.kareem.littlelemon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.kareem.littlelemon.util.AppConstants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Database Models
@Entity(tableName = "menu_items")
data class MenuItemRoom(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val image: String,
    val description: String
)

// Network Models
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

// Cart Models
data class CartItem(
    val menuItem: MenuItemRoom,
    val quantity: Int = 1
)

// Database DAO
@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM menu_items) == 0")
    fun isEmpty(): Boolean
}

// Database
@Database(entities = [MenuItemRoom::class], version = AppConstants.Database.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
} 
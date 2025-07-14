package com.kareem.littlelemon.data.repository

import androidx.lifecycle.LiveData
import com.kareem.littlelemon.data.AppDatabase
import com.kareem.littlelemon.data.MenuItemNetwork
import com.kareem.littlelemon.data.MenuItemRoom
import com.kareem.littlelemon.data.MenuNetwork
import com.kareem.littlelemon.util.AppConstants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MenuRepository(private val database: AppDatabase) {
    
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    // Database operations
    fun getAllMenuItems(): LiveData<List<MenuItemRoom>> {
        return database.menuItemDao().getAll()
    }

    suspend fun insertMenuItems(menuItems: List<MenuItemRoom>) {
        withContext(Dispatchers.IO) {
            database.menuItemDao().insertAll(*menuItems.toTypedArray())
        }
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return withContext(Dispatchers.IO) {
            database.menuItemDao().isEmpty()
        }
    }

    // Network operations
    suspend fun fetchMenuFromNetwork(url: String): List<MenuItemNetwork> {
        return withContext(Dispatchers.IO) {
            val response: HttpResponse = httpClient.get(url)
            val menuData: MenuNetwork = response.body()
            menuData.menu
        }
    }

    // Combined operations
    suspend fun fetchMenuIfNeeded() {
        if (isDatabaseEmpty()) {
//            val url = "https://raw.githubusercontent.com/KareemElnagar/Working-With-Data-API/main/menu.json"
            val menuItemsNetwork = fetchMenuFromNetwork(AppConstants.Api.MENU_URL)
            val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
            insertMenuItems(menuItemsRoom)
        }
    }
} 
package com.kareem.littlelemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.kareem.littlelemon.util.LittleLemonApplication
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    //Network client
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))

        }
    }

    // Database Build
    private val database: AppDatabase = Room.databaseBuilder(LittleLemonApplication.getApplicationContext(), AppDatabase::class.java, "database").build()

    //return menu form database
    fun getAllDatabaseMenuItems(): LiveData<List<MenuItemRoom>> {
        return database.menuItemDao().getAll()
    }

    // return menu from API
    fun fetchMenuIfNeeded() {
        viewModelScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val url =
                    "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
                val menuItemsNetwork = fetchMenu(url)
                saveMenuToDatabase(database, menuItemsNetwork)
            }
        }
    }

    //API response
    private suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
        val response: HttpResponse = httpClient.get(url)
        val menuData: MenuNetwork = response.body()
        return menuData.menu


    }

    // Cashing API response to room database
    private fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }


}
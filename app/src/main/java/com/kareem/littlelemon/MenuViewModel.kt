package com.kareem.littlelemon

import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class MenuViewModel() : ViewModel() {
    //Network client
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))

        }
    }

    private suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
        val response: HttpResponse = httpClient.get(url)
        val menuData: MenuNetwork = response.body()
        return menuData.menu


    }
    private fun saveMenuToDatabase(database: AppDatabase,menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

}
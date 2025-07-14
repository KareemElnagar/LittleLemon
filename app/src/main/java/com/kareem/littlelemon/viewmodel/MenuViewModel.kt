package com.kareem.littlelemon.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kareem.littlelemon.data.CartItem
import com.kareem.littlelemon.data.MenuItemRoom
import com.kareem.littlelemon.data.repository.CartRepository
import com.kareem.littlelemon.data.repository.MenuRepository
import com.kareem.littlelemon.di.DependencyInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    // Repositories
    private val menuRepository: MenuRepository = DependencyInjection.menuRepository
    private val cartRepository: CartRepository = DependencyInjection.cartRepository

    // UI State
    var selectedDish by mutableStateOf(0)
    var selectedCategory by mutableStateOf("All")
    
    // Cart state
    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set
    
    var cartTotal by mutableStateOf(0.0)
        private set
    
    var cartItemCount by mutableStateOf(0)
        private set

    init {
        // Observe cart changes
        viewModelScope.launch {
            cartRepository.cartItems.collectLatest { items ->
                cartItems = items
                cartTotal = cartRepository.getCartTotal()
                cartItemCount = cartRepository.getCartItemCount()
            }
        }
    }

    // Menu operations
    fun getAllDatabaseMenuItems(): LiveData<List<MenuItemRoom>> {
        return menuRepository.getAllMenuItems()
    }

    fun fetchMenuIfNeeded() {
        viewModelScope.launch {
            try {
                menuRepository.fetchMenuIfNeeded()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Cart operations
    fun addToCart(menuItem: MenuItemRoom) {
        cartRepository.addToCart(menuItem)
    }

    fun removeFromCart(menuItemId: Int) {
        cartRepository.removeFromCart(menuItemId)
    }

    fun updateCartItemQuantity(menuItemId: Int, quantity: Int) {
        cartRepository.updateQuantity(menuItemId, quantity)
    }

    fun clearCart() {
        cartRepository.clearCart()
    }

    // Category operations
    fun getCategoryList(): List<String> {
        return listOf("All", "Starters", "Mains", "Dessert", "Drinks")
    }

    fun updateSelectedCategory(category: String) {
        selectedCategory = category
    }
}
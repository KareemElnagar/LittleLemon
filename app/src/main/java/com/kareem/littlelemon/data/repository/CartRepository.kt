package com.kareem.littlelemon.data.repository

import com.kareem.littlelemon.data.CartItem
import com.kareem.littlelemon.data.MenuItemRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepository {
    
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    fun addToCart(menuItem: MenuItemRoom) {
        val currentItems = _cartItems.value.toMutableList()
        val existingItemIndex = currentItems.indexOfFirst { it.menuItem.id == menuItem.id }
        
        if (existingItemIndex != -1) {
            // Item already exists, increment quantity
            val existingItem = currentItems[existingItemIndex]
            currentItems[existingItemIndex] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            // Add new item
            currentItems.add(CartItem(menuItem))
        }
        
        _cartItems.value = currentItems
    }

    fun removeFromCart(menuItemId: Int) {
        val currentItems = _cartItems.value.toMutableList()
        currentItems.removeAll { it.menuItem.id == menuItemId }
        _cartItems.value = currentItems
    }

    fun updateQuantity(menuItemId: Int, quantity: Int) {
        val currentItems = _cartItems.value.toMutableList()
        val itemIndex = currentItems.indexOfFirst { it.menuItem.id == menuItemId }
        
        if (itemIndex != -1) {
            if (quantity <= 0) {
                currentItems.removeAt(itemIndex)
            } else {
                val existingItem = currentItems[itemIndex]
                currentItems[itemIndex] = existingItem.copy(quantity = quantity)
            }
            _cartItems.value = currentItems
        }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun getCartTotal(): Double {
        return _cartItems.value.sumOf { it.menuItem.price * it.quantity }
    }

    fun getCartItemCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }
} 
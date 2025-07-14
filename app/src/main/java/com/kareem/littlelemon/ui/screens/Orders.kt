package com.kareem.littlelemon.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.kareem.littlelemon.viewmodel.MenuViewModel
import com.kareem.littlelemon.data.MenuItemRoom
import com.kareem.littlelemon.ui.components.CartItemCard

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Orders(navController: NavHostController, sharedMenuViewModel: MenuViewModel) {
    val cartItems = sharedMenuViewModel.cartItems

    if (cartItems.isEmpty()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "No orders yet")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(cartItems) { cartItem ->
                CartItemCard(
                    cartItem = cartItem,
                    onQuantityIncrease = { sharedMenuViewModel.updateCartItemQuantity(cartItem.menuItem.id, cartItem.quantity + 1) },
                    onQuantityDecrease = { sharedMenuViewModel.updateCartItemQuantity(cartItem.menuItem.id, cartItem.quantity - 1) },
                    onRemove = { sharedMenuViewModel.removeFromCart(cartItem.menuItem.id) }
                )
            }
        }
    }
}
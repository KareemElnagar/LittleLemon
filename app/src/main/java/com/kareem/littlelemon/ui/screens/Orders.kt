package com.kareem.littlelemon.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kareem.littlelemon.ui.components.CartItemCard
import com.kareem.littlelemon.ui.theme.PrimaryGreen
import com.kareem.littlelemon.ui.theme.PrimaryYellow
import com.kareem.littlelemon.util.showCheckoutNotification
import com.kareem.littlelemon.viewmodel.MenuViewModel

@Composable
fun Orders(navController: NavHostController, sharedMenuViewModel: MenuViewModel) {
    val cartItems = sharedMenuViewModel.cartItems
    val context = LocalContext.current

    val totalPrice = cartItems.sumOf { it.menuItem.price * it.quantity }

    if (cartItems.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "No orders yet")
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PrimaryYellow)
                ) {
                    Text("Your Orders", modifier = Modifier.padding(16.dp))
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(cartItems) { cartItem ->
                        CartItemCard(
                            cartItem = cartItem,
                            onQuantityIncrease = {
                                sharedMenuViewModel.updateCartItemQuantity(
                                    cartItem.menuItem.id,
                                    cartItem.quantity + 1
                                )
                            },
                            onQuantityDecrease = {
                                sharedMenuViewModel.updateCartItemQuantity(
                                    cartItem.menuItem.id,
                                    cartItem.quantity - 1
                                )
                            },
                            onRemove = {
                                sharedMenuViewModel.removeFromCart(cartItem.menuItem.id)
                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = .1f))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total: $${"%.2f".format(totalPrice)}",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                androidx.compose.material3.Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Check out successful, Your Order is being prepared. ",
                            Toast.LENGTH_LONG
                        ).show()
                        sharedMenuViewModel.clearCart()
                        showCheckoutNotification(context)

                    },
                    enabled = totalPrice > 0,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryYellow,
                        contentColor = PrimaryGreen
                    )
                ) {
                    Text("Checkout")
                }
            }
        }
    }
}

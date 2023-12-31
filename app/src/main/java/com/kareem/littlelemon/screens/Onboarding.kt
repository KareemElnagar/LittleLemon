package com.kareem.littlelemon.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kareem.littlelemon.R
import com.kareem.littlelemon.ui.theme.PrimaryGreen
import com.kareem.littlelemon.ui.theme.PrimaryYellow
import com.kareem.littlelemon.util.Constants
import com.kareem.littlelemon.util.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                Modifier.width(150.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = PrimaryGreen)
                    .height(120.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "Let's get to know you",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 30.sp
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Personal information",
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text(text = "First name") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = {
                        Text(
                            text = "Last name",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            text = "Email",
                        )
                    },


                )
                Spacer(modifier = Modifier.height(80.dp))


            }
            Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)) {
                    OutlinedButton(
                        onClick = {
                            if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                                Toast.makeText(
                                    context,
                                    "Registration not completed. Please enter all data.",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                sharedPreferences(context, firstName, lastName, email)
                                Toast.makeText(
                                    context,
                                    "Registration completed!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                navController.navigate(Home.route)


                            }
                        },
                        Modifier.fillMaxWidth(),
                        border = BorderStroke(2.dp, PrimaryGreen),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryYellow,
                            contentColor = PrimaryGreen
                        )
                    ) {
                        Text(
                            text = "Register",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    Onboarding(navController)
}

fun sharedPreferences(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPreferences = context.getSharedPreferences(Constants.USER_KEY, Context.MODE_PRIVATE)
    sharedPreferences.edit().apply {
        putString(Constants.FIRST_NAME_KEY, firstName)
        putString(Constants.LAST_NAME_KEY, lastName)
        putString(Constants.EMAIL_KEY, email)
        putBoolean(Constants.REGISTER_KEY, true)
    }.apply()
}
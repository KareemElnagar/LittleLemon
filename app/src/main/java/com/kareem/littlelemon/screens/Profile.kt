package com.kareem.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kareem.littlelemon.R
import com.kareem.littlelemon.ui.theme.PrimaryGreen
import com.kareem.littlelemon.ui.theme.PrimaryYellow
import com.kareem.littlelemon.util.Constants
import com.kareem.littlelemon.util.Onboarding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(Constants.USER_KEY, Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString(Constants.FIRST_NAME_KEY, "") ?: "Little"
    val lastName = sharedPreferences.getString(Constants.LAST_NAME_KEY, "") ?: "Lemon"
    val email = sharedPreferences.getString(Constants.EMAIL_KEY, "") ?: "LittleLemon@kareem.com"
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)

            )
        }



        Column(
            Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profile information",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.headlineLarge
            )


        }
        Spacer(modifier = Modifier.size(40.dp))



        Column(
            Modifier
                .height(300.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly

        ) {
            Text(
                text = "First Name",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Last Name",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Email Address",
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyLarge
            )
            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            )


        }

        Spacer(modifier = Modifier.size(21.dp))

        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        val editor = sharedPreferences.edit()
                        editor.clear()
                        editor.apply()
                        navController.navigate(Onboarding.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryYellow,
                        contentColor = PrimaryGreen
                    )

                ) {
                    Text(
                        text = "Log out",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                    )

                }

            }

        }


    }

}


@Preview(showBackground = false)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController)
}
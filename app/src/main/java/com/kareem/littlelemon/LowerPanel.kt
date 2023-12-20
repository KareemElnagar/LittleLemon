package com.kareem.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LowerPanel() {
    WeeklySpecial()
    MenuDish()
}
@Composable
fun WeeklySpecial() {
    // to be defined
    Card(
        Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.LightGray)
    )


    {
        Text(
            text = "Weekly Specials",
            fontSize = 26.sp,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold,


            )


    }

}


@Composable
fun MenuDish() {
    // to be defined
    Card(
        colors = CardDefaults.cardColors(Color.White)

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),

            ) {
            Column() {
                Text(
                    text = "Greek Salad",
                    fontSize = 18.sp,

                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = "The famous Greek salad of crispy lettuce, peppers, olives, our Chicago ...",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f),
                    color = Color.Gray


                )
                Text(
                    text = "$12.99",
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(5.dp),
                    fontWeight = FontWeight.Bold


                )

            }
            Image(
                painter = painterResource(id = R.drawable.greeksalad),
                contentDescription = "greek salad photo"
            )

        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}
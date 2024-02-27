package com.kareem.littlelemon.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kareem.littlelemon.MenuItemRoom
import com.kareem.littlelemon.ui.theme.HighlightBlack
import com.kareem.littlelemon.ui.theme.PrimaryGreen
import com.kareem.littlelemon.ui.theme.PrimaryYellow
import com.kareem.littlelemon.ui.theme.Smoke

@Composable
fun ChipGroup(databaseMenuItem: List<MenuItemRoom>, categoryLambda: (selected: String) -> Unit) {
    val categories = databaseMenuItem.map {
        it.category.replaceFirstChar { char ->
            char.uppercase()
        }
    }.toSet()

    var selected by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Chip(title = "All", selected = selected, onSelected = {
            selected = it
            categoryLambda(it.lowercase())
        })
        categories.forEach { it ->
            Chip(title = it, selected = selected, onSelected = {
                selected = it
                categoryLambda(it)
            })

        }

    }
}

@Composable
fun Chip(
    title: String,
    selected: String,
    onSelected: (String) -> Unit
) {
    var isSelected = selected == title

    val background = if (isSelected) PrimaryYellow else Smoke
    val contentColor = if (isSelected) HighlightBlack else PrimaryGreen
    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .height(35.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                onSelected(title)
                isSelected = !isSelected
            })
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AnimatedVisibility(visible = isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Checked",
                    tint = Color.White
                )

            }
            Text(text = title, color = contentColor, fontSize = 16.sp)

        }
    }

}
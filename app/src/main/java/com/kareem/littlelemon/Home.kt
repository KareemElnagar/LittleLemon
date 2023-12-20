package com.kareem.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home() {
    Column {
        UpperPanel()
        LowerPanel()
    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home()
}
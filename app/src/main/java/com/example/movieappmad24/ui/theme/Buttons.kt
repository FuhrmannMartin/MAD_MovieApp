package com.example.movieappmad24.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomButton(icon: ImageVector, text: String, isSelected: Boolean) {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Black,
                modifier = Modifier
                    .background(if (isSelected) Color.Gray.copy(alpha = 0.4f) else Color.Transparent)
        )
        Text(
            text = text,
            style = TextStyle(fontSize = 12.sp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview(){
    BottomButton(Icons.Default.Star, "Watchlist", false)
    // BottomButton(Icons.Default.Star, "Watchlist")
}

package com.masdika.pelidoku.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Number input pad layout
@Composable
fun NumberInputPad(
    onNumberClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        (1..9).forEach { number ->
            NumberButton(
                onClick = { onNumberClick(number) },
                number = number.toShort(),
                modifier = Modifier
                    .width(40.dp)
                    .height(50.dp)
            )
        }
    }
}

// Styling NumberButton view
@Composable
fun NumberButton(
    onClick: () -> Unit,
    number: Short,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 1.dp,
            pressedElevation = 2.dp
        ),
        modifier = modifier
    ) {
        Text(
            text = number.toString(),
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
            )
        )
    }
}

// Class for NumberInputPad Button interface component for filling the board
package com.masdika.pelidoku.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.masdika.pelidoku.ui.components.SudokuBoard

@Composable
fun HomeScreen() {
//    val dummyBoard = List(9) { List(9) { 0 } }
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val board = List(9) { List(9) { 0 } }

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SudokuBoard(
            board = board,
            selectedCell = selectedCell,
            onCellClick = { row, col ->
                selectedCell = row to col
            },
        )
    }
}

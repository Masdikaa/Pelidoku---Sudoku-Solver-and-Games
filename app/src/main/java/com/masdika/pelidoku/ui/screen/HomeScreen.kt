package com.masdika.pelidoku.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.masdika.pelidoku.ui.components.NumberInputPad
import com.masdika.pelidoku.ui.components.SudokuBoard

@Composable
fun HomeScreen() {
    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
//    val board = List(9) { List(9) { 0 } }
    var board by remember { mutableStateOf(List(9) { List(9) { 0 } }) }

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display Board
        SudokuBoard(
            board = board,
            selectedCell = selectedCell,
            onCellClick = { row, col ->
                selectedCell = row to col
            },
        )

        Spacer(modifier = Modifier.height(24.dp)) // Spacer for Numpad

        NumberInputPad(
            onNumberClick = { number ->
                selectedCell?.let { (row, col) ->

                    // Copy board with new number inputted
                    val newBoard = board.mapIndexed { r, rowList ->
                        if (r == row) {
                            rowList.mapIndexed { c, cellValue ->
                                if (c == col) number else cellValue
                            }
                        } else {
                            rowList
                        }
                    }
                    board = newBoard // Update state board

                }
            }
        )

    }
}

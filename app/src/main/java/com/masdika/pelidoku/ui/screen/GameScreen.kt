package com.masdika.pelidoku.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.masdika.pelidoku.ui.components.NumberInputPad
import com.masdika.pelidoku.ui.components.SudokuBoard
import com.masdika.pelidoku.ui.components.TopAppBarGame

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GameScreen() {

    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var board by remember { mutableStateOf(List(9) { List(9) { 0 } }) }

    ConstraintLayout {
        val (
            topAppBarGame,
            mistakesText,
            difficultiesText,
            timerText,
            sudokuBoard,
            numberInputPad
        ) = createRefs()
        val startGuideline = createGuidelineFromStart(0.025f)
        val endGuideline = createGuidelineFromEnd(0.025f)
        val bottomGuideline = createGuidelineFromBottom(0.08f)
        val topGameBoardGuideline = createGuidelineFromTop(0.2f)

        TopAppBarGame(
            modifier = Modifier.constrainAs(topAppBarGame) {
                top.linkTo(parent.top)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }
        )

        Text(
            modifier = Modifier.constrainAs(mistakesText) {
                start.linkTo(sudokuBoard.start, margin = 2.dp)
                bottom.linkTo(sudokuBoard.top, margin = 5.dp)
            },
            text = "Mistake : 0"
        )

        Text(
            modifier = Modifier.constrainAs(difficultiesText) {
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
                bottom.linkTo(sudokuBoard.top, margin = 5.dp)
            },
            text = "Extreme"
        )

        Text(
            modifier = Modifier.constrainAs(timerText) {
                end.linkTo(sudokuBoard.end, margin = 2.dp)
                bottom.linkTo(sudokuBoard.top, margin = 5.dp)
            },
            text = "00:00"
        )

        SudokuBoard(
            board = board,
            selectedCell = selectedCell,
            onCellClick = { row, col ->
                selectedCell = row to col
            },
            modifier = Modifier.constrainAs(sudokuBoard) {
                width = Dimension.fillToConstraints
                top.linkTo(topGameBoardGuideline)
                start.linkTo(startGuideline)
                end.linkTo(endGuideline)
            }
        )

        NumberInputPad(
            modifier = Modifier.constrainAs(numberInputPad) {
                bottom.linkTo(bottomGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
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
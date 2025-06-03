package com.masdika.pelidoku.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.masdika.pelidoku.ui.theme.selectedCell
import com.masdika.pelidoku.ui.theme.shadowCell
import com.masdika.pelidoku.ui.theme.sudokuBorder

@Composable
fun SudokuBoard(
    board: List<List<Int>>,
    selectedCell: Pair<Int, Int>?,
    onCellClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = MaterialTheme.colorScheme.sudokuBorder

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val cellSize = size.width / 9
                        val row = (offset.y / cellSize).toInt().coerceIn(0, 8)
                        val col = (offset.x / cellSize).toInt().coerceIn(0, 8)
                        onCellClick(row, col)
                    },
                    onDrag = { change, _ ->
                        val cellSize = size.width / 9
                        val row = (change.position.y / cellSize).toInt().coerceIn(0, 8)
                        val col = (change.position.x / cellSize).toInt().coerceIn(0, 8)
                        onCellClick(row, col)
                    }
                )
            }
    ) {
        val cellSize = this.maxWidth / 9
        Column {
            for (i in board.indices) {
                Row {
                    for (j in board[i].indices) {
                        val cell = board[i][j]

                        val borderStart = if (j % 3 == 0) 2.5.dp else 1.dp
                        val borderTop = if (i % 3 == 0) 2.5.dp else 1.dp
                        val borderEnd = if (j == 8) 2.5.dp else 1.dp
                        val borderBottom = if (i == 8) 2.5.dp else 1.dp

                        val isSelected = selectedCell?.first == i && selectedCell.second == j
                        val isSameRowOrCol = selectedCell != null && (selectedCell.first == i || selectedCell.second == j)
                        val isSameBox = selectedCell?.let { isInSameBox(i, j, it.first, it.second) } == true

                        val isShadow = (isSameRowOrCol || isSameBox) && !isSelected

                        val backgroundColor = when {
                            isSelected -> MaterialTheme.colorScheme.selectedCell
                            isShadow -> MaterialTheme.colorScheme.shadowCell.copy(alpha = 0.5f)
                            else -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .background(backgroundColor)
                                .clickable { onCellClick(i, j) }
                                .border(
                                    BorderStroke(
                                        width = 0.dp,
                                        brush = SolidColor(Color.Transparent)
                                    )
                                )
                                .drawBehind {
                                    drawLine(
                                        color = borderColor,
                                        start = Offset(0f, 0f),
                                        end = Offset(0f, size.height),
                                        strokeWidth = borderStart.toPx()
                                    )
                                    drawLine(
                                        color = borderColor,
                                        start = Offset(0f, 0f),
                                        end = Offset(size.width, 0f),
                                        strokeWidth = borderTop.toPx()
                                    )
                                    drawLine(
                                        color = borderColor,
                                        start = Offset(size.width, 0f),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = borderEnd.toPx()
                                    )
                                    drawLine(
                                        color = borderColor,
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = borderBottom.toPx()
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (cell == 0) "" else cell.toString(),
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                                )
                                /*
                                    Number in box style
                                */
                            )
                        }
                    }
                }
            }
        }
    }
}

fun isInSameBox(i: Int, j: Int, selectedRow: Int, selectedCol: Int): Boolean {
    return (i / 3 == selectedRow / 3) && (j / 3 == selectedCol / 3)
}


/*
 Region
 +---------+----------+--------+
 |3, 0, 6, | 5, 0, 8, | 4, 0, 0|
 |5, 2, 0, | 0, 0, 0, | 0, 0, 0|
 |0, 8, 7, | 0, 0, 0, | 0, 3, 1|
 +---------+----------+--------+
 |0, 0, 3, | 0, 1, 0, | 0, 8, 0|
 |9, 0, 0, | 8, 6, 3, | 0, 0, 5|
 |0, 5, 0, | 0, 9, 0, | 6, 0, 0|
 +---------+----------+--------+
 |1, 3, 0, | 0, 0, 0, | 2, 5, 0|
 |0, 0, 0, | 0, 0, 0, | 0, 7, 4|
 |0, 0, 5, | 2, 0, 6, | 3, 0, 0|
 +---------+----------+--------+

 Grid
 +---------+
 |3, 0, 6, |
 |5, 2, 0, |
 |0, 8, 7, |
 +---------+

 Block
 +---+
 | 9 |
 +---+
 */

package com.masdika.pelidoku.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.masdika.pelidoku.R

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarGame(modifier: Modifier = Modifier) {

    val isDarkTheme = isSystemInDarkTheme()

    CenterAlignedTopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
            ) {
                val (backIconButton, textTitle, utilityButtonRow) = createRefs()
                val startGuideline = createGuidelineFromStart(0.03f)
                val endGuideline = createGuidelineFromEnd(0.03f)
                val buttonUtilityGuideline = createGuidelineFromStart(0.75f)

                Icon(
                    modifier = Modifier
                        .height(26.dp)
                        .width(26.dp)
                        .constrainAs(backIconButton) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(startGuideline)
                        },
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back Button",
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    modifier = Modifier.constrainAs(textTitle) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(backIconButton.end, margin = 10.dp)
                    },
                    text = "Pelidoku",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    modifier = Modifier
                        .constrainAs(utilityButtonRow) {
                            width = Dimension.fillToConstraints
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(endGuideline)
                            start.linkTo(buttonUtilityGuideline)
                        },
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        modifier = Modifier
                            .height(26.dp)
                            .width(26.dp),
                        painter = painterResource(
                            if (isDarkTheme) R.drawable.light_mode_icon
                            else R.drawable.dark_mode_icon
                        ),
                        contentDescription = "Theme Setting Button",
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Icon(
                        modifier = Modifier
                            .height(26.dp)
                            .width(26.dp),
                        painter = painterResource(id = R.drawable.setting_icon),
                        contentDescription = "Setting Button",
                    )

                }

            }
        }
    )
}
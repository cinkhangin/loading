package com.naulian.loading.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naulian.motion.animateSimpleFloat
import com.naulian.motion.lerp

//Day 5
//animation by Heather Romney https://lottiefiles.com/yfc428a1g3zsmxna
//https://lottiefiles.com/free-animation/loading-dots-blue-on-white-ZugaGItk38
@Composable
fun Pendulum(
    modifier: Modifier = Modifier,
    maxBallSize: Dp = 24.dp,
    spacedBy: Dp = maxBallSize / 4,
    ballColor: Color = MaterialTheme.colorScheme.primary
) {

    val (ballSize, spacing, radius) = with(LocalDensity.current) {
        val size = maxBallSize.toPx()
        Triple(first = size, second = spacedBy.toPx(), third = size / 2)
    }

    val (widthDp, heightDp) = with(LocalDensity.current) {
        Pair(first = (ballSize * 5 + spacing * 4).toDp(), second = maxBallSize * 3)
    }

    val infiniteAnim = rememberInfiniteTransition()

    val left by infiniteAnim.animateSimpleFloat( //update motion
        duration = 600,
        delay = 600,
        easing = LinearOutSlowInEasing,
        repeatMode = RepeatMode.Reverse,
        label = "left",
    )

    val right by infiniteAnim.animateSimpleFloat(
        duration = 600,
        delay = 600,
        startDelay = 1200,
        easing = LinearOutSlowInEasing,
        repeatMode = RepeatMode.Reverse,
        label = "left",
    )

    Canvas(
        modifier = modifier
            .width(widthDp)
            .height(heightDp)
    ) {

        val leftAnim = lerp(input = left, outStart = 0f, outEnd = 60f)
        rotate(leftAnim, pivot = Offset(x = radius, y = 0f)) {
            drawCircle(
                color = ballColor,
                radius = radius,
                center = Offset(
                    x = radius,
                    y = size.height - radius
                )
            )
        }


        drawCircle(
            color = ballColor,
            radius = radius,
            center = Offset(
                x = ballSize + spacing + radius,
                y = size.height - radius
            )
        )

        drawCircle(
            color = ballColor,
            radius = radius,
            center = Offset(
                x = ballSize * 2 + spacing * 2 + radius,
                y = size.height - radius
            )
        )

        drawCircle(
            color = ballColor,
            radius = radius,
            center = Offset(
                x = ballSize * 3 + spacing * 3 + radius,
                y = size.height - radius
            )
        )

        val rightAnim = lerp(
            input = right, outStart = 0f, outEnd = 60f
        )
        rotate(360f - rightAnim, pivot = Offset(x = ballSize * 4 + spacing * 4 + radius, y = 0f)) {
            drawCircle(
                color = ballColor,
                radius = radius,
                center = Offset(
                    x = ballSize * 4 + spacing * 4 + radius,
                    y = size.height - radius
                )
            )
        }
    }
}

@Preview
@Composable
private fun CircleWavePreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFf3f6f8), RoundedCornerShape(12.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Pendulum(
            ballColor = Color(0xFF3F51B5),
        )
    }
}
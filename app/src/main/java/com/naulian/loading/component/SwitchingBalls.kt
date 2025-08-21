package com.naulian.loading.component

import androidx.compose.animation.core.CubicBezierEasing
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naulian.motion.animateSimpleFloat
import com.naulian.motion.lerp

//Day 3
//animation by Arpan Gupta https://lottiefiles.com/arpangupta
//https://lottiefiles.com/free-animation/loading-RPJRbJJhDY
@Composable
fun SwitchingBalls(
    modifier: Modifier = Modifier,
    ballSize: Dp = 24.dp,
    spacedBy: Dp = ballSize / 3,
    ballColor : Color = MaterialTheme.colorScheme.primary
) {

    val (size, spacing, radius) = with(LocalDensity.current) {
        Triple(
            ballSize.toPx(),
            spacedBy.toPx(),
            ballSize.toPx() / 2
        )
    }

    val (widthDp, heightDp) = with(LocalDensity.current) {
        Pair(
            ((size * 2) + spacing).toDp(),
            ballSize
        )
    }

    val infiniteAnim = rememberInfiniteTransition()
    val animation by infiniteAnim.animateSimpleFloat(
        duration = 600,
        repeatMode = RepeatMode.Reverse,
        easing = CubicBezierEasing(0.7f, 0.0f, 0.3f, 1.0f),
        label = "ball1",
    )

    Canvas(
        modifier = modifier
            .width(widthDp)
            .height(heightDp)
    ) {
        drawCircle(
            color = ballColor,
            radius = radius,
            center = Offset(
                x = lerp(
                    input = animation,
                    outStart = radius,
                    outEnd = size + spacing + radius
                ),
                y = radius
            )
        )

        drawCircle(
            color = ballColor,
            radius = radius,
            center = Offset(
                x = lerp(
                    input = animation,
                    outStart = size + spacing + radius,
                    outEnd = radius
                ),
                y = radius
            ),
            style = Stroke(width = 2f)
        )
    }
}

@Preview
@Composable
private fun SwitchingBallsPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFf3f6f8), RoundedCornerShape(12.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        SwitchingBalls(
            ballColor = Color(0xFF3F51B5),
            ballSize = 48.dp
        )
    }
}
package com.naulian.loading.component

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
import androidx.compose.runtime.remember
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

//animation by Maximilian Dahl @https://lottiefiles.com/maximiliandahl
//@https://lottiefiles.com/free-animation/car2go-global-loading-animation-Vzv61jcw3T
@Composable
fun CircleWave(
    modifier: Modifier = Modifier,
    maxBallSize: Dp = 48.dp,
    spacedBy: Dp = maxBallSize / 4,
    ballColor: Color = MaterialTheme.colorScheme.primary
) {

    val (ballSize, spacing, radius) = with(LocalDensity.current) {
        val size = maxBallSize.toPx()
        Triple(first = size, second = spacedBy.toPx(), third = size / 2)
    }

    val (widthDp, heightDp) = with(LocalDensity.current) {
        Pair(first = (ballSize * 4 + spacing * 3).toDp(), second = maxBallSize)
    }

    val infiniteAnim = rememberInfiniteTransition()
    val position by infiniteAnim.animateSimpleFloat(
        duration = 700,
        repeatMode = RepeatMode.Restart,
        label = "circle_wave",
    )

    val scale by infiniteAnim.animateSimpleFloat(
        duration = 700,
        repeatMode = RepeatMode.Restart,
        label = "circle_wave_scale",
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
                    inStart = 0f,
                    inEnd = 0.25f,
                    input = position,
                    outStart = radius,
                    outEnd = ballSize + spacing + radius
                ),
                y = radius
            )
        )

        drawCircle(
            color = ballColor,
            radius = radius,
            center = Offset(
                x = lerp(
                    input = position,
                    outStart = ballSize + spacing + radius,
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
private fun CircleWavePreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFf3f6f8), RoundedCornerShape(12.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircleWave(
            ballColor = Color(0xFF3F51B5),
        )
    }
}
package com.naulian.loading.component

import androidx.annotation.FloatRange
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naulian.loading.bounceLerp
import com.naulian.loading.map
import com.naulian.motion.animateSimpleFloat
import com.naulian.motion.lerp

fun Float.scale(@FloatRange(0.0, 1.0) percent: Float): Float {
    return (this * percent)
}

//animation by Maximilian Dahl https://lottiefiles.com/maximiliandahl
//https://lottiefiles.com/free-animation/car2go-global-loading-animation-Vzv61jcw3T
@Composable
fun CircleWave(
    modifier: Modifier = Modifier,
    maxBallSize: Dp = 64.dp,
    spacedBy: Dp = maxBallSize / 2,
    ballColor: Color = MaterialTheme.colorScheme.primary
) {

    val (ballSize, spacing, radius) = with(LocalDensity.current) {
        val size = maxBallSize.toPx()
        Triple(first = size, second = spacedBy.toPx(), third = size / 2)
    }

    val (widthDp, heightDp) = with(LocalDensity.current) {
        Pair(first = (ballSize * 3 + spacing * 2).toDp(), second = maxBallSize)
    }

    val infiniteAnim = rememberInfiniteTransition()

    val position by infiniteAnim.animateSimpleFloat(
        duration = 2000,
        repeatMode = RepeatMode.Restart,
        label = "circle_wave_pos",
    )

    Canvas(
        modifier = modifier
            .width(widthDp)
            .height(heightDp)
    ) {

        val anim1 = map(position, 0f)
        val scaler1 = bounceLerp(input = anim1)
        drawCircle(
            color = ballColor,
            radius = radius.scale(scaler1),
            center = Offset(
                x = lerp(
                    input = anim1,
                    outStart = radius,
                    outEnd = size.width - radius
                ),
                y = radius
            )
        )

        val anim2 = map(position, 0.33f)
        val scaler2 = bounceLerp(input = anim2)
        drawCircle(
            color = ballColor,
            radius = radius.scale(scaler2),
            center = Offset(
                x = lerp(
                    input = anim2,
                    outStart = radius,
                    outEnd = size.width - radius
                ),
                y = radius
            )
        )

        val anim3 = map(position, 0.66f)
        val scaler3 = bounceLerp(input = anim3)
        drawCircle(
            color = ballColor,
            radius = radius.scale(scaler3),
            center = Offset(
                x = lerp(
                    input = anim3,
                    outStart = radius,
                    outEnd = size.width - radius
                ),
                y = radius
            )
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
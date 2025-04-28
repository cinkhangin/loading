package com.naulian.loading.component

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
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
import com.naulian.loading.theme.Blue
import com.naulian.loading.theme.Green
import com.naulian.loading.theme.Orange
import com.naulian.loading.theme.Red
import com.naulian.loading.theme.Yellow
import com.naulian.motion.animateSimpleFloat
import com.naulian.motion.lerp

//Day 1
//animation by LottieFiles https://lottiefiles.com/lottiefilez
//https://lottiefiles.com/free-animation/material-wave-loading-yt2uoeE83o
@Composable
fun MaterialWave(
    modifier: Modifier = Modifier,
    ballSizeDp: Dp = 24.dp,
    spacedBy: Dp = 16.dp,
    gapDelay: Int = 200,
    pause : Int = 500,
    animDuration : Int = 500,
    easing : Easing = FastOutSlowInEasing
) {

    val (ballSize, spacing, radius) = with(LocalDensity.current) {
        Triple(
            ballSizeDp.toPx(),
            spacedBy.toPx(),
            ballSizeDp.toPx() / 2
        )
    }

    val (widthDp, heightDp) = with(LocalDensity.current) {
        Pair(
            ((ballSize * 5) + (spacing * 4)).toDp(),
            (ballSize * 2 + spacing).toDp()
        )
    }

    val infiniteAnim = rememberInfiniteTransition()

    val first by infiniteAnim.animateSimpleFloat(
        duration = animDuration,
        delay = pause,
        label = "first_ball",
        easing = easing
    )
    val second by infiniteAnim.animateSimpleFloat(
        duration = animDuration,
        delay = pause,
        label = "second_ball",
        startDelay = gapDelay,
        easing = easing
    )
    val third by infiniteAnim.animateSimpleFloat(
        duration = animDuration,
        delay = pause,
        label = "third_ball",
        startDelay = gapDelay * 2,
        easing = easing
    )
    val forth by infiniteAnim.animateSimpleFloat(
        duration = animDuration,
        delay = pause,
        label = "forth_ball",
        startDelay = gapDelay * 3,
        easing = easing
    )
    val fifth by infiniteAnim.animateSimpleFloat(
        duration = animDuration,
        delay = pause,
        label = "fifth_ball",
        startDelay = gapDelay * 4,
        easing = easing
    )

    Canvas(
        modifier = modifier
            .width(widthDp)
            .height(heightDp)
    ) {
        drawCircle(
            color = Red,
            radius = radius,
            center = Offset(
                x = radius,
                y = lerp(
                    input = first,
                    outStart = size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Blue,
            radius = radius,
            center = Offset(
                x = ballSize + spacing + radius,
                y = lerp(
                    input = second,
                    outStart = size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Green,
            radius = radius,
            center = Offset(
                x = (ballSize * 2) + (spacing * 2) + radius,
                y = lerp(
                    input = third,
                    outStart = size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Yellow,
            radius = radius,
            center = Offset(
                x = (ballSize * 3) + (spacing * 3) + radius,
                y = lerp(
                    input = forth,
                    outStart = size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Orange,
            radius = radius,
            center = Offset(
                x = (ballSize * 4) + (spacing * 4) + radius,
                y = lerp(
                    input = fifth,
                    outStart = size.height - radius,
                    outEnd = radius
                )
            )
        )
    }
}

@Preview
@Composable
private fun MaterialWavePreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFf3f6f8), RoundedCornerShape(12.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        MaterialWave()
    }
}
package com.naulian.loading.component

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import com.naulian.loading.theme.LinearOutFastInEasing
import com.naulian.loading.theme.Orange
import com.naulian.loading.theme.Red
import com.naulian.loading.theme.Yellow


@Suppress("SpellCheckingInspection")
fun lerp(
    input: Float,
    inStart: Float = 0f,
    inEnd: Float = 1f,
    outStart: Float = 0f,
    outEnd: Float = 1f
): Float {
    if (input > inEnd) return outEnd
    if (input < inStart) return outStart
    return outStart + (outEnd - outStart) * (input - inStart) / (inEnd - inStart)
}

@Suppress("SpellCheckingInspection")
fun bounceLerp(
    input: Float,
    inStart: Float = 0f,
    inEnd: Float = 1f,
    outStart: Float = 0f,
    outEnd: Float = 1f,
    outEasing : Easing = LinearOutSlowInEasing,
    inEasing : Easing = LinearOutFastInEasing
): Float {
    val halfWay = ((inEnd - inStart) / 2) + inStart
    return if (input <= halfWay) {
        val raw = lerp(input, inStart, halfWay, outStart, outEnd)
        outEasing.transform(raw)
    } else {
        val raw =  lerp(input, halfWay, inEnd, outEnd, outStart)
        inEasing.transform(raw)
    }
}

//animation by LottieFiles
//@https://lottiefiles.com/free-animation/material-wave-loading-yt2uoeE83o
@Composable
fun MaterialWave(
    modifier: Modifier = Modifier,
    ballSize: Dp = 24.dp,
    spacedBy: Dp = 16.dp
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
            ((size * 5) + (spacing * 4)).toDp(),
            (size * 2 + spacing).toDp()
        )
    }

    val infiniteAnim = rememberInfiniteTransition()
    val animation by infiniteAnim.animateFloat(
        initialValue = 0f,
        targetValue = 1.2f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing
            )
        ),
        label = "material_wave",
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
                y = bounceLerp(
                    input = animation,
                    inStart = 0f,
                    inEnd = 0.6f,
                    outStart = this.size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Blue,
            radius = radius,
            center = Offset(
                x = size + spacing + radius,
                y = bounceLerp(
                    input = animation,
                    inStart = 0.15f,
                    inEnd = 0.75f,
                    outStart = this.size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Green,
            radius = radius,
            center = Offset(
                x = (size * 2) + (spacing * 2) + radius,
                y = bounceLerp(
                    input = animation,
                    inStart = 0.30f,
                    inEnd = 0.90f,
                    outStart = this.size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Yellow,
            radius = radius,
            center = Offset(
                x = (size * 3) + (spacing * 3) + radius,
                y = bounceLerp(
                    input = animation,
                    inStart = 0.45f,
                    inEnd = 1.05f,
                    outStart = this.size.height - radius,
                    outEnd = radius
                )
            )
        )

        drawCircle(
            color = Orange,
            radius = radius,
            center = Offset(
                x = (size * 4) + (spacing * 4) + radius,
                y = bounceLerp(
                    input = animation,
                    inStart = 0.60f,
                    inEnd = 1.2f,
                    outStart = this.size.height - radius,
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
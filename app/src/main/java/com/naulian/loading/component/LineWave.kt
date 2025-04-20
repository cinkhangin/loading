package com.naulian.loading.component

import android.util.Log
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.naulian.loading.theme.Blue
import com.naulian.loading.theme.LinearOutFastInEasing

@Composable
fun InfiniteTransition.animateSimpleFloat(
    duration: Int = 1000,
    easing: Easing = LinearEasing,
    label: String,
    delay: Int = 0,
    startDelay: Int = 0,
    repeatMode: RepeatMode = RepeatMode.Reverse
) = animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = InfiniteRepeatableSpec(
        animation = tween(
            durationMillis = duration,
            easing = easing,
            delayMillis = delay,
        ),
        repeatMode = repeatMode,
        initialStartOffset = StartOffset(startDelay)
    ),
    label = label,
)

//animation by Anastasiya Remeslova
//@https://lottiefiles.com/free-animation/loading-18-Sx9I9v8DOW
@Composable
fun LineWave(
    modifier: Modifier = Modifier,
    thickness: Dp = 10.dp,
    height: Dp = 50.dp,
    spacing: Dp = thickness,
    animationDuration: Int = 700,
    easing: Easing = LinearOutFastInEasing,
    delay: Int = 120
) {
    val (thicknessPx, spacingPx, heightPx) = with(LocalDensity.current) {
        Triple(
            thickness.toPx(),
            spacing.toPx(),
            height.toPx()
        )
    }

    val width = with(LocalDensity.current) {
        ((thicknessPx * 5) + (spacingPx * 4)).toDp()
    }

    val infiniteAnim = rememberInfiniteTransition()

    val anim1 by infiniteAnim.animateSimpleFloat(
        duration = animationDuration,
        label = "line_wave1",
        easing = easing,
        startDelay = 0
    )

    val anim2 by infiniteAnim.animateSimpleFloat(
        duration = animationDuration,
        label = "line_wave2",
        easing = easing,
        startDelay = delay
    )

    val anim3 by infiniteAnim.animateSimpleFloat(
        duration = animationDuration,
        label = "line_wave3",
        easing = easing,
        startDelay = delay * 2
    )

    val anim4 by infiniteAnim.animateSimpleFloat(
        duration = animationDuration,
        label = "line_wave4",
        easing = easing,
        startDelay = delay * 3
    )

    val anim5 by infiniteAnim.animateSimpleFloat(
        duration = animationDuration,
        label = "line_wave5",
        easing = easing,
        startDelay = delay * 4
    )

    Log.d("LineWave", "recomposition")

    Canvas(
        modifier = modifier
            .width(width)
            .height(height)
    ) {
        val centerY = heightPx / 2

        val line1 = lerp(
            input = anim1,
            outStart = 2f,
            outEnd = heightPx,
        )

        val color1 = lerp(
            input = anim1,
            outStart = 0.1f,
            outEnd = 1f,
        )

        drawLine(
            color = Blue.copy(alpha = color1),
            start = Offset(
                x = thicknessPx / 2,
                y = centerY - (line1 / 2)
            ),
            end = Offset(
                x = thicknessPx / 2,
                y = centerY + (line1 / 2)
            ),
            strokeWidth = thicknessPx,
            cap = StrokeCap.Round
        )


        val line2 = lerp(
            input = anim2,
            outStart = 2f,
            outEnd = heightPx
        )

        val color2 = lerp(
            input = anim2,
            outStart = 0.1f,
            outEnd = 1f,
        )

        drawLine(
            color = Blue.copy(alpha = color2),
            start = Offset(
                x = thicknessPx + spacingPx + thicknessPx / 2,
                y = centerY - (line2 / 2)
            ),
            end = Offset(
                x = thicknessPx + spacingPx + thicknessPx / 2,
                y = centerY + (line2 / 2)
            ),
            strokeWidth = thicknessPx,
            cap = StrokeCap.Round
        )

        val line3 = lerp(
            input = anim3,
            outStart = 2f,
            outEnd = heightPx
        )

        val color3 = lerp(
            input = anim3,
            outStart = 0.1f,
            outEnd = 1f,
        )

        drawLine(
            color = Blue.copy(alpha = color3),
            start = Offset(
                x = thicknessPx * 2 + spacingPx * 2 + thicknessPx / 2,
                y = centerY - (line3 / 2)
            ),
            end = Offset(
                x = thicknessPx * 2 + spacingPx * 2 + thicknessPx / 2,
                y = centerY + (line3 / 2)
            ),
            strokeWidth = thicknessPx,
            cap = StrokeCap.Round
        )

        val line4 = lerp(
            input = anim4,
            outStart = 2f,
            outEnd = heightPx
        )

        val color4 = lerp(
            input = anim4,
            outStart = 0.1f,
            outEnd = 1f,
        )

        drawLine(
            color = Blue.copy(alpha = color4),
            start = Offset(
                x = thicknessPx * 3 + spacingPx * 3 + thicknessPx / 2,
                y = centerY - (line4 / 2)
            ),
            end = Offset(
                x = thicknessPx * 3 + spacingPx * 3 + thicknessPx / 2,
                y = centerY + (line4 / 2)
            ),
            strokeWidth = thicknessPx,
            cap = StrokeCap.Round
        )

        val line5 = lerp(
            input = anim5,
            outStart = 2f,
            outEnd = heightPx
        )

        val color5 = lerp(
            input = anim5,
            outStart = 0.1f,
            outEnd = 1f,
        )

        drawLine(
            color = Blue.copy(alpha = color5),
            start = Offset(
                x = thicknessPx * 4 + spacingPx * 4 + thicknessPx / 2,
                y = centerY - (line5 / 2)
            ),
            end = Offset(
                x = thicknessPx * 4 + spacingPx * 4 + thicknessPx / 2,
                y = centerY + (line5 / 2)
            ),
            strokeWidth = thicknessPx,
            cap = StrokeCap.Round
        )
    }
}

@Preview
@Composable
private fun LineWavePreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFf3f6f8), RoundedCornerShape(12.dp))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        LineWave()
    }
}
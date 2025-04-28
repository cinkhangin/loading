package com.naulian.loading

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import com.naulian.loading.theme.SlowOutFastInEasing
import com.naulian.motion.lerp

@Suppress("SpellCheckingInspection")
fun bounceLerp(
    input: Float,
    inStart: Float = 0f,
    inEnd: Float = 1f,
    outStart: Float = 0f,
    outEnd: Float = 1f,
    outEasing: Easing = FastOutSlowInEasing,
    inEasing: Easing = SlowOutFastInEasing
): Float {
    val halfWay = ((inEnd - inStart) / 2) + inStart
    return if (input <= halfWay) {
        val raw = lerp(input, inStart, halfWay, outStart, outEnd)
        outEasing.transform(raw)
    } else {
        val raw = lerp(input, halfWay, inEnd, outEnd, outStart)
        inEasing.transform(raw)
    }
}

//to annotate function that are not ready
annotation class NotReady

/**
 * Maps a value in the range [0.0, 1.0] based on a start offset.
 *
 * @param value The input value in range [0.0, 1.0]
 * @param startOffset The offset value in range [0.0, 1.0]
 * @return The mapped value in range [0.0, 1.0]
 */

fun map(
    @FloatRange(0.0, 1.0) value: Float,
    @FloatRange(0.0, 1.0) startOffset: Float
): Float {
    return lerp(
        input = if (value <= startOffset) 1f + value else value,
        inStart = startOffset,
        inEnd = 1f + startOffset
    )
}

fun Float.flip() = 1f - this

fun main() {
    listOf(
        0.0f, 0.05f, 0.1f, 0.15f, 0.2f, 0.25f, 0.3f, 0.35f, 0.4f, 0.45f, 0.5f,
        0.55f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1.0f
    ).forEach {
        //println("$it -> ${map(it, 0.25f)}")
        println("$it -> ${it.flip()}")
    }
}

fun Float.scale(@FloatRange(0.0, 1.0) percent: Float): Float {
    return (this * percent)
}


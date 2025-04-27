package com.naulian.loading

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearOutSlowInEasing
import com.naulian.loading.theme.LinearOutFastInEasing
import com.naulian.motion.lerp

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
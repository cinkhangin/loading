package com.naulian.loading.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing

val LinearOutFastInEasing: Easing = CubicBezierEasing(0.0f, 0.0f, 0.1f, 0.4f)
val SlowOutFastInEasing: Easing = CubicBezierEasing(1.0f, 0.2f, 0.0f, 0.4f)

fun main() {
    val list = listOf(0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f)
    list.forEach {
        println(FastOutSlowInEasing.transform(it))
    }
}
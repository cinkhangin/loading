package com.naulian.loading

import kotlin.system.measureTimeMillis

//210 ms
fun Int.isEven1() = this % 2 == 0

//200 ms
fun Int.isEven2() = this and 1 == 0

//354 ms
fun Int.isEven3() = "$this".last() in "02468"


fun main() {
    println(
        buildList {
            repeat(100) {
                val time = measureTimeMillis {
                    var str = ""
                    repeat(10000000) {
                        str = "$it:${it.isEven3()}"
                    }
                    println(str)
                }
                println("run$it : $time ms")
                add(time)
            }
        }.average()
    )

}
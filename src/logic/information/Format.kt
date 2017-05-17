package logic.information

fun Double.format() = String.format("%,.0f", this)
fun Int.format() = String.format("%,d", this)

fun Double.percentage() = "${String.format("%.2f", this * 100)} %"

fun Information<*>.asString() = "$name: $value"
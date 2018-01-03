import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    var x = 0

    println(
            measureTimeMillis {
                for (i in 0 until 10_000_000_000) {
                    x = add(x)
                }
            }
    )

    var y = 0
    println(
            measureTimeMillis {
                for (i in 0 until 2_000_000_000) {
                    y += 1
                }
            }
    )

    print("y: $y")
}

fun add(x: Int) = x + 1
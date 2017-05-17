package utils

private var randomSeed: Int = System.currentTimeMillis().toInt()

fun randomFast(): Int {
    randomSeed = randomSeed xor (randomSeed shl 13)
    randomSeed = randomSeed xor (randomSeed shr 17)
    randomSeed = randomSeed xor (randomSeed shl 5)
    return randomSeed
}

/**
 * Returns a pseudo random number. The calculation is designed to be fast.
 *
 * name: xorshift32
 * source: http://www.javamex.com/tutorials/random_numbers/xorshift.shtml
 * http://stackoverflow.com/questions/13213395/adjusting-xorshift-generator-to-return-a-number-within-a-maximum
 * @param upperBound exlusive
 */
fun randomFast(upperBound: Int): Int {
    randomFast()
    val out = randomSeed % upperBound
    return if (out < 0) -out else out
}


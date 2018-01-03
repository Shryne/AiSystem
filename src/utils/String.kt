package utils

/**
 * Fills the given string with the given characters to reach the amount of characters. The characters are added to the
 * front of the string.
 *
 * source: https://stackoverflow.com/a/6185386
 */
fun String.fill(with: String, amount: Int) =
        String.format("%1\$" + amount + "s", this).replace(" ", with) // padding to 24 chars
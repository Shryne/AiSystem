package base.mill

fun move(index: Int) = 1 shl index
fun move(source: Int, target: Int) = (1 shl source) or (1 shl target)

// TODO: Is it immutable?
val transitions = hashMapOf(
        move(0).to(arrayOf(move(1), move(9))),
        move(1).to(arrayOf(move(0), move(2), move(4))),
        move(2).to(arrayOf(move(1), move(4))),

        move(3).to(arrayOf(move(4), move(10))),
        move(4).to(arrayOf(move(1), move(3), move(5), move(7))),
        move(5).to(arrayOf(move(4), move(13))),

        move(6).to(arrayOf(move(7), move(11))),
        move(7).to(arrayOf(move(4), move(6), move(8))),
        move(8).to(arrayOf(move(7), move(12))),

        move(9).to(arrayOf(move(0), move(10), move(21))),
        move(10).to(arrayOf(move(3), move(9), move(11), move(18))),
        move(11).to(arrayOf(move(6), move(10), move(15))),

        move(12).to(arrayOf(move(8), move(13), move(17))),
        move(13).to(arrayOf(move(5), move(12), move(14), move(20))),
        move(14).to(arrayOf(move(2), move(13), move(23))),

        move(15).to(arrayOf(move(11), move(16))),
        move(16).to(arrayOf(move(15), move(17), move(19))),
        move(17).to(arrayOf(move(12), move(16))),

        move(18).to(arrayOf(move(10), move(19))),
        move(19).to(arrayOf(move(16), move(18), move(20), move(22))),
        move(20).to(arrayOf(move(13), move(19))),

        move(21).to(arrayOf(move(9), move(22))),
        move(22).to(arrayOf(move(19), move(21), move(23))),
        move(23).to(arrayOf(move(14), move(22)))
)

val isValidMove get() = true
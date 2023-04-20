package day1

fun main() {
    println(data.map { it.toLong() }.sumOf { x(it).sum() })
}

fun x(number: Long) = sequence {
    var sum = number
    do {
        sum = (sum / 3) - 2
        if (sum > 0) yield(sum)
    } while (sum > 0)
}

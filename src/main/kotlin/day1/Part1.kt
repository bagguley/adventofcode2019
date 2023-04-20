package day1

fun main() {
    println(data.map { it.toLong() }.sumOf { (it / 3) - 2 })
}

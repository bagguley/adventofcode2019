package day3

fun main() {
    val circuit = Circuit.load(data)
    println(circuit.findShortestCrossover())
}
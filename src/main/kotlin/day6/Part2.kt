package day6

fun main() {
    val orbits = Orbits.load(data)
    println(orbits.countOrbitTransfers("YOU", "SAN"))
}
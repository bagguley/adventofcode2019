package day6

class Orbits(private val orbits: Map<String, Planet>) {

    companion object Load {
        fun load(input: List<String>): Orbits {
            val map = mutableMapOf<String, Planet>()

            for (line in input) {
                val split = line.split(")")
                val planet = map.getOrPut(split[1]) { Planet(split[1]) }
                val orbits = map.getOrPut(split[0]) { Planet(split[0]) }
                planet.orbits(orbits)
            }
            return Orbits(map)
        }
    }

    fun countOrbits(): Int {
        val count = mutableMapOf<String, Int>()
        return orbits.values.sumOf { countOrbits(count, it) }
    }

    private fun countOrbits(count: MutableMap<String, Int>, planet: Planet): Int {
        if (planet.orbits == null) return 0
        return 1 + count.getOrPut(planet.name) {
            countOrbits(count, planet.orbits!!)
        }
    }

    private fun orbitsOf(planetName: String): MutableList<String> {
        return orbitsOf(orbits[planetName]!!, mutableListOf())
    }

    private fun orbitsOf(planet: Planet, orbitsList: MutableList<String>): MutableList<String> {
        if (planet.orbits == null) return orbitsList
        orbitsList.add(planet.orbits!!.name)
        return orbitsOf(planet.orbits!!, orbitsList)
    }

    private fun commonOrbit(first: String, second: String): String {
        val firstOrbits = orbitsOf(first)
        val secondOrbits = orbitsOf(second)
        secondOrbits.removeIf { !firstOrbits.contains(it) }
        return secondOrbits.first()
    }

    private fun distance(from: String, to: String): Int {
        val fromPlanet = orbits[from]!!
        var count = 0
        var next = fromPlanet.orbits!!
        while (next.name != to) {
            next = next.orbits!!
            count++
        }
        return count
    }

    fun countOrbitTransfers(from: String, to: String): Int {
        val mostRecentCommon = commonOrbit(from, to)
        val first = distance(from, mostRecentCommon)
        val second = distance(to, mostRecentCommon)
        return first + second
    }
}
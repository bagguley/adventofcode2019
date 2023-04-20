package day6

class Planet(val name: String) {
    var orbits: Planet? = null

    fun orbits(planet: Planet) {
        orbits = planet
    }
}
package day3

import java.lang.RuntimeException
import kotlin.math.abs
import kotlin.math.min

class Circuit(private val wires: List<List<String>>) {
    private val circuitMap = mutableMapOf<Pair<Int,Int>, MutableMap<Int, Int>>()

    init {
        runWires()
    }

    companion object Load {
        fun load(input: List<String>): Circuit {
            val wires = mutableListOf<List<String>>()
            for (line in input) {
                wires.add(line.split(","))
            }
            return Circuit(wires)
        }
    }

    private fun findCrossOvers(): Map<Pair<Int, Int>, MutableMap<Int, Int>> {
        return circuitMap.filter { it.value.size > 1 }
    }

    private fun findCrossOverDistances(): List<Int> {
        return findCrossOvers().map{ e -> abs(e.key.first) + abs(e.key.second) }
    }

    fun findClosestCrossOver(): Int {
        return findCrossOverDistances().min()
    }

    fun findShortestCrossover(): Int {
        return findCrossOvers().map { entry -> entry.value.values.sum() }.min()
    }

    private fun runWires() {
        wires.forEachIndexed{ idx, wire -> runWire(idx, wire) }
    }

    private fun runWire(idx: Int, wire: List<String>) {
        var x = 0
        var y = 0
        var count = 1
        for (section in wire) {
            var dx = 0
            var dy = 0
            when(section[0]) {
                'U' -> dy = -1
                'R' -> dx = 1
                'D' -> dy = 1
                'L' -> dx = -1
                else -> throw RuntimeException("Bad instruction")
            }

            val sectionLength = section.substring(1).toInt()

            for (i in 1..sectionLength) {
                x += dx
                y += dy
                val map = circuitMap.getOrPut(Pair(x, y)){mutableMapOf()}
                val v = map.getOrPut(idx){count}
                map[idx] = min(v, count)
                count++
            }
        }
    }
}
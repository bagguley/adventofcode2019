package day4

fun main() {
    val start = data[0].toInt()
    val end = data[1].toInt()
    val result = mutableListOf<String>()

    for (i in start..end) {
        if (Part1.isValid(i.toString())) {
            result.add(i.toString())
        }
    }

    println(result.size)
}

object Part1 {
    fun isValid(value: String): Boolean {
        return hasTwoAdjacentDigits(value) && digitsIncrease(value)
    }

    private fun digitsIncrease(value: String): Boolean {
        var digit = '0'
        for (c in value) {
            if (c < digit) return false
            digit = c
        }
        return true
    }

    private fun hasTwoAdjacentDigits(value: String): Boolean {
        for (i in 0 until value.length - 1) {
            if (value[i] == value[i + 1]) return true
        }
        return false
    }
}
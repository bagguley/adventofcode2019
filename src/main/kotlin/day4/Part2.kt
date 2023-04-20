package day4

fun main() {
    println(Part2.calc(data))
}

object Part2 {
    fun calc(input: List<String>) {
        val start = input[0].toInt()
        val end = input[1].toInt()
        val result = mutableListOf<String>()

        for (i in start..end) {
            if (isValid(i.toString())) {
                result.add(i.toString())
            }
        }

        println(result.size)
    }

    private fun isValid(value: String): Boolean {
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
        var i = 0
        val list = mutableListOf<Int>()

        while (i < value.length - 1) {
            val c = countConsecutiveDigits(i, value)
            list.add(c)
            i += c
        }

        return list.contains(2)
    }

    private fun countConsecutiveDigits(start: Int, string: String): Int {
        val digit = string[start]
        var count = 1
        while (count + start < string.length) {
            if (string[count + start] == digit) {
                count++
            } else break
        }
        return count
    }
}
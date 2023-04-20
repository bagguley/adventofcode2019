package day7

class Computer(private val instructions: MutableList<String>) {

    var output = ""

    companion object Load {
        fun load(input: List<String>): Computer {
            return Computer(input.toMutableList())
        }
    }

    fun run(phaseSetting: String, input: String) {
        val inputList = mutableListOf(phaseSetting, input)
        var pc = 0

        do {
            var running = true
            val instruction = instructions[pc].padStart(5, '0')
            val modes = instruction.substring(0, 3).toCharArray()

            when (instruction.substring(3)) {
                "01" -> {
                    val value = read(modes[2], instructions[pc+1]).toInt() + read(modes[1], instructions[pc+2]).toInt()
                    write(modes[0], instructions[pc+3], value.toString())
                    pc += 4
                }
                "02" -> {
                    val value = read(modes[2], instructions[pc+1]).toInt() * read(modes[1], instructions[pc+2]).toInt()
                    write(modes[0], instructions[pc+3], value.toString())
                    pc += 4
                }
                "03" ->  {
                    write(modes[2], instructions[pc+1], inputList.removeFirst())
                    pc += 2
                }
                "04" -> {
                    output(modes[2], instructions[pc+1])
                    pc += 2
                }
                "05" -> {
                    val first = read(modes[2], instructions[pc+1])
                    val second = read(modes[1], instructions[pc+2])
                    if (first.toInt() != 0) {
                        pc = second.toInt()
                    } else {
                        pc += 3
                    }
                }
                "06" -> {
                    val first = read(modes[2], instructions[pc+1])
                    val second = read(modes[1], instructions[pc+2])
                    if (first.toInt() == 0) {
                        pc = second.toInt()
                    } else {
                        pc += 3
                    }
                }
                "07" -> {
                    val first = read(modes[2], instructions[pc+1])
                    val second = read(modes[1], instructions[pc+2])
                    if (first < second) {
                        write(modes[0], instructions[pc+3], "1")
                    } else {
                        write(modes[0], instructions[pc+3], "0")
                    }
                    pc += 4
                }
                "08" -> {
                    val first = read(modes[2], instructions[pc+1])
                    val second = read(modes[1], instructions[pc+2])
                    if (first == second) {
                        write(modes[0], instructions[pc+3], "1")
                    } else {
                        write(modes[0], instructions[pc+3], "0")
                    }
                    pc += 4
                }
                "99" -> {
                    running = false
                }
                else -> throw RuntimeException("Bad opcode")
            }
        } while (running)
    }

    private fun read(mode:Char, value: String): String {
        return when (mode) {
            '0' -> {
                val result = instructions[value.toInt()]
                result
            }

            '1' -> {
                value
            }

            else -> throw RuntimeException("Bad read mode")
        }
    }

    private fun write(mode: Char, addr: String, value: String) {
        when (mode) {
            '0' -> {
                instructions[addr.toInt()] = value
            }

            else -> throw RuntimeException("Bad write mode $mode, $addr, $value")
        }
    }

    private fun output(mode:Char, value: String) {
        when (mode) {
            '0' -> {
                val target = instructions[value.toInt()].toInt()
                output += target
            }
            '1' -> {
                output += value
            }
            else -> throw RuntimeException("Bad write mode")
        }
    }
}
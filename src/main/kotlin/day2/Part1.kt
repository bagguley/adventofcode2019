package day2

fun main() {
    var pc = 0
    var ins: Int
    val program = data.toMutableList()
    program[1] = 12
    program[2] = 2
    do {
        ins = program[pc]
        when (ins) {
            1 -> program[program[pc+3]] = program[program[pc+1]] + program[program[pc+2]]
            2 -> program[program[pc+3]] = program[program[pc+1]] * program[program[pc+2]]
        }
        pc += 4
    } while (ins != 99)
    println(program[0])
}
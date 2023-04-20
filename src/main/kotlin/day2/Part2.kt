package day2

fun main() {

    for (noun in 0..99) {
        for (verb in 0..99) {
            val num = calc(noun, verb)
            if (num == 19690720) {
                println(100 * noun + verb)
                break
            }
        }
    }
}

fun calc(noun: Int, verb: Int) : Int {
    var pc = 0
    var ins: Int
    val program = data.toMutableList()
    program[1] = noun
    program[2] = verb
    do {
        ins = program[pc]
        when (ins) {
            1 -> program[program[pc+3]] = program[program[pc+1]] + program[program[pc+2]]
            2 -> program[program[pc+3]] = program[program[pc+1]] * program[program[pc+2]]
        }
        pc += 4
    } while (ins != 99)

    return program[0]
}
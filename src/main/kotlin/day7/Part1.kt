package day7

fun main() {
    val resultList = mutableListOf<String>()
    val phase = Phase()

    while (!phase.done()) {
        println(phase.getPhase())
        val computer1 = Computer.load(testData1)
        val computer2 = Computer.load(testData1)
        val computer3 = Computer.load(testData1)
        val computer4 = Computer.load(testData1)
        val computer5 = Computer.load(testData1)

        computer1.run(phase.getPhase0(), "0")
        computer2.run(phase.getPhase1(), computer1.output)
        computer3.run(phase.getPhase2(), computer2.output)
        computer4.run(phase.getPhase3(), computer3.output)
        computer5.run(phase.getPhase4(), computer4.output)
        resultList.add(computer5.output)
        phase.increment()
    }

    println(resultList.max())
}
package day7

class Phase {
    var int = 0

    fun getPhase0(): String {
        return getPhase(4).toString()
    }

    fun getPhase1(): String {
        return getPhase(3).toString()
    }

    fun getPhase2(): String {
        return getPhase(2).toString()
    }

    fun getPhase3(): String {
        return getPhase(1).toString()
    }

    fun getPhase4(): String {
        return getPhase(0).toString()
    }

    fun increment() {
        int++
    }

    fun done(): Boolean {
        return int >= 5*4*3*2
    }

    fun getPhase(): String {
        return int.toString(5).padStart(5, '0')
    }

    private fun getPhase(index: Int): Char {
        return getPhase()[index]
    }
}
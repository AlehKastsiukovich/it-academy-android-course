package by.training.task2.operations

fun calculateArithmeticalMean(list: List<Int>): Double {
    val elementsSum = list.sum()
    return elementsSum / list.size.toDouble()
}

fun calculateElementsSum(list: List<Int>) = list.sum()

fun calculateSeparationOperation(list: List<Int>): Double {
    val listHalf = list.size / 2

    var elementsSum = 0
    for (i in 0 until listHalf) {
        elementsSum += list[i]
    }

    var deduction = list[listHalf]
    for (i in (listHalf + 1) until list.size) {
        deduction -= list[i]
    }

    return elementsSum / deduction.toDouble()
}

@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import java.io.File.separator
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var a = 0.0
    for (i in v.indices) {
        a += sqr(v[i])
    }
    a = sqrt(a)
    return a
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var number = 0.0
    if (list.isNotEmpty()) number = list.sum() / list.size
    return number
}


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val total = list.sum() / list.size
        for (i in list.indices) {
            list[i] -= total
        }
    }
    return list
}

/**
 * Средня
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var multiplier = 0
    if (a.isNotEmpty() && b.isNotEmpty()) {
        for (i in a.indices) {
            multiplier += a[i] * b[i]
        }
    }
    return multiplier
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var total = 0
    var y = 1
    if (p.isNotEmpty()) {
        for (i in p.indices) {
            total += p[i] * y
            y *= x
        }
    }
    return total
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isNotEmpty()) {
        for (i in list.indices) {
            if (i != 0) {
                list[i] += list[i - 1]
            }
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    var list = mutableListOf<Int>()
    var digit = 2
    while (number > 1) {
        while (number % digit == 0) {
            number /= digit
            list.add(digit)
        }
        digit++
    }
    list.sorted()
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var remain = 0
    var list = mutableListOf<Int>()
    var number = n
    while (number > base) {
        remain = number % base
        number /= base
        list.add(remain)
    }
    list.add(number)
    list.reverse()
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val letters = mutableListOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    )
    val numbers = mutableListOf(
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
        20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35
    )
    var a = 0
    var b = ""
    var result = convert(n, base)
    var total = mutableListOf<String>()
    for (i in result.indices) {
        if (result[i] > 9) {
            a = result[i]
            for (i in letters.indices) {
                if (a == numbers[i]) b = letters[i]
            }
            total.add(b)
        } else total.add(result[i].toString())
    }
    return total.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0.0
    var b = base.toDouble()
    var total1 = 0.0
    var total2 = 0
    var degree = digits.size - 1
    for (i in digits.indices) {
        number = digits[i].toDouble()
        total1 += b.pow(degree) * number
        degree -= 1
    }
    total2 = total1.toInt()
    return total2
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val letters = mutableListOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    )
    val numbers = mutableListOf(
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
        20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35
    )
    val digits = mutableListOf(
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    )
    var result = str.toMutableList()
    var total = mutableListOf<Int>()
    var a = ""
    var b = 0
    for (i in result.indices) {
        a = result[i].toString()
        for (i in letters.indices) {
            if (a == letters[i]) total.add(numbers[i])
        }
        if (total.size != i) {
            b = result[i].toInt()
            for (i in digits.indices) {
                if (b == digits[i]) total.add(digits[i])
            }
        }
    }
    println(b)
    return decimal(total, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var number = n
    var result = ""
    while (number >= 1000) {
        result += "M"
        number -= 1000
    }
    while (number >= 900) {
        result += "CM"
        number -= 900
    }
    while (number >= 500) {
        result += "D"
        number -= 500
    }
    while (number >= 400) {
        result += "CD"
        number -= 400
    }
    while (number >= 100) {
        result += "C"
        number -= 100
    }
    while (number >= 90) {
        result += "XC"
        number -= 90
    }
    while (number >= 50) {
        result += "L"
        number -= 50
    }
    while (number >= 40) {
        result += "XL"
        number -= 40
    }
    while (number >= 10) {
        result += "X"
        number -= 10
    }
    while (number >= 9) {
        result += "IX"
        number -= 9
    }
    while (number >= 5) {
        result += "V"
        number -= 5
    }
    while (number >= 4) {
        result += "IV"
        number -= 4
    }
    while (number >= 1) {
        result += "I"
        number -= 1
    }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var digit = digitNumber(n)
    var count = 0
    var number = n.toString().trim().split("").toMutableList()
    if (number[0] == "") number.remove(number[0])
    var numbers1 = listOf(
        "", "один", "два", "три", "четыре", "пять", "шесть", "семь"
        , "восемь", "девять"
    )
    var numbers5 = listOf(
        "десять", "одинадцать", "двенадцать", "тринадцать",
        "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    )
    var numbers2 = listOf(
        "", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят",
        "девяносто"
    )
    var numbers3 =
        listOf("", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    var numbers4 = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    var total = ""
    if (digit > 3) {
        if (digit == 6) {
            for (j in numbers4) {
                if (number[0].toInt() == numbers4[j]) count = j
            }
            for (i in numbers3.indices) {
                if (count == i) {
                    total += " "
                    total += numbers3[i]
                    number.remove(number[0])
                    digit--
                }
            }
        }
        println(number)
        if ((digit == 5) && (number[0].toInt() != 0) && (number[0].toInt() != 1)) {
            for (j in numbers4) {
                print(number[0])
                if (number[0].toInt() == numbers4[j]) count = j
            }
            for (i in numbers2.indices) {
                if (count == i) {
                    total += " "
                    print(numbers2[i])
                    if (numbers2[i] =="два") total += "две"
                    else total += numbers2[i]
                    number.remove(number[0])
                    digit--
                }
            }
        }
        if (number[0].toInt() == 1) {
            for (j in numbers4) {
                if (number[1].toInt() == numbers4[j]) count = j
            }
            for (i in numbers5.indices) {
                if (count == i) {
                    total += " "
                    total += numbers5[i]
                    number.remove(number[0])
                    number.remove(number[0])
                    digit -= 2
                }
            }
        }
        if (number[0].toInt() == 0) {
            number.remove(number[0])
            digit--
        }
        if (digit == 4) {
            for (j in numbers4) {
                if (number[0].toInt() == numbers4[j]) count = j
            }
            for (i in numbers1.indices) {
                if (count == i) {
                    total += " "
                    total += numbers1[i]
                }
            }
        }
        if (number[0].toInt() == 1) total += " тысяча "
        total += if ((number[0].toInt() == 2) || (number[0].toInt() == 3) || (number[0].toInt() == 4)) " тысячи "
        else " тысяч "
        number.remove(number[0])
    }
    if (digit == 3)
        for (j in numbers4) {
            if (number[0].toInt() == numbers4[j]) count = j
        }
    for (i in numbers3.indices) {
        if (count == i) {
            total += " "
            total += numbers3[i]
            number.remove(number[0])
            digit--
        }
    }
    if ((digit == 2) && (number[0].toInt() != 0) && (number[0].toInt() != 1)) {
        for (j in numbers4) {
            if (number[0].toInt() == numbers4[j]) count = j
        }
        for (i in numbers2.indices) {
            if (count == i) {
                total += " "
                total += numbers2[i]
                number.remove(number[0])
                digit--
            }
        }
    } else {
        if (number[0].toInt() == 0) {
            number.remove(number[0])
            digit--
        }
        if (number[0].toInt() == 1) {
            for (j in numbers4) {
                if (number[1].toInt() == numbers4[j]) count = j
            }
            for (i in numbers5.indices) {
                if (count == i) {
                    total += " "
                    total += numbers5[i]
                    number.remove(number[0])
                    number.remove(number[0])
                    digit -= 2
                }
            }
        }
    }
    println(total)
    if (digit == 1) {
        for (j in numbers4) {
            if (number[0].toInt() == numbers4[j]) count = j
        }
        for (i in numbers1.indices) {
            if (count == i) {
                total += " "
                total += numbers1[i]
            }
        }
    }
    return total.trim()
}

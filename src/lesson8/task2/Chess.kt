@file:Suppress("UNUSED_PARAMETER")

package lesson8.task2

import java.lang.IllegalArgumentException
import kotlin.math.abs

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String {
        if ((column <= 0) || (row <= 0) || (column > 8) || (row > 8)) return ""
        var word = (" a b c d e f g h").split(" ")
        var total = ""
        for (i in word.indices) {
            if ((column == i) && (i != 0)) {
                total = word[i]
                return "$total$row"
            }
        }
        return ""
    }
}

/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    var count = 0
    if (notation.length < 2) throw IllegalArgumentException()
    var not = notation.trim().split("")
    var word = listOf("a", "b", "c", "d", "e", "f", "g", "h")
    for (i in word.indices) {
        if (not[2].contains(word[i])) throw IllegalArgumentException()
    }
    for (i in word.indices) {
        if (!not[1].contains(word[i])) {
            count++
            if (count == 8) throw IllegalArgumentException()
        }
    }
    var number = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    var column = 0
    for (i in word.indices) {
        if (word[i] == not[1]) {
            column = number[i]
        }
    }
    val row = not[2]
    return Square(column, row.toInt())
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int {
    if ((start.row > 8) || (start.row <= 0) || (end.column > 8) || (end.column <= 0)) throw IllegalArgumentException()
    var count = 0
    if (start.row != end.row) count++
    if (start.column != end.column) count++
    return count
}

/**
 * Средняя
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> = TODO()

/**
 * Простая
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int = TODO()

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> = TODO()

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int {
    if ((start.row > 8) || (start.column > 8) || (start.row <= 0) || (start.column <= 0)
        || (end.row > 8) || (end.column > 8) || (end.row <= 0) || (end.column <= 0)
    ) throw IllegalArgumentException()
    var count = 0
    var rowdifference = abs(start.row - end.row)
    var columndifference = abs(start.column - end.column)
    while ((rowdifference != 0) && (columndifference != 0)) {
        rowdifference--
        columndifference--
        count++
    }
    while (rowdifference != 0) {
        rowdifference--
        count++
    }
    while (columndifference != 0) {
        columndifference--
        count++
    }
    return count
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> {
    if ((start.row > 8) || (start.column > 8) || (start.row <= 0) || (start.column <= 0)
        || (end.row > 8) || (end.column > 8) || (end.row <= 0) || (end.column <= 0)
    ) throw IllegalArgumentException()
    var way = mutableListOf(start)
    if (start == end) return way
    var x = 0
    var y = 0
    var rowdifference = end.row - start.row
    var columndifference = end.column - start.column
    if (rowdifference == 0) {
        while (columndifference > 0) {
            columndifference--
            x++
            way.add(Square(start.column + x, start.row + y))
        }
        while  (columndifference < 0) {
            columndifference++
            x--
            way.add(Square(start.column + x, start.row + y))
        }
    }
    if (columndifference == 0) {
        while (rowdifference > 0)  {
            rowdifference--
            y++
            way.add(Square(start.column + x, start.row + y))
        }
        while (rowdifference < 0) {
            rowdifference++
            y--
            way.add(Square(start.column + x, start.row + y))
        }
    }
    if ((rowdifference > 0) && (columndifference > 0)) {
        while ((rowdifference != 0) && (columndifference != 0)) {
            rowdifference--
            columndifference--
            y++
            x++
            way.add(Square(start.column + x, start.row + y))
        }
        while (rowdifference != 0) {
            rowdifference--
            y++
            way.add(Square(start.column + x, start.row + y))
        }
        while (columndifference != 0) {
            columndifference--
            x++
            way.add(Square(start.column + x, start.row + y))
        }
    }
    if ((rowdifference < 0) && (columndifference > 0)) {
        while ((rowdifference != 0) && (columndifference != 0)) {
            rowdifference++
            columndifference--
            y--
            x++
            way.add(Square(start.column + x, start.row + y))
        }
        while (rowdifference != 0) {
            rowdifference++
            y--
            way.add(Square(start.column + x, start.row + y))
        }
        while (columndifference != 0) {
            columndifference--
            x++
            way.add(Square(start.column + x, start.row + y))
        }
    }
    if ((rowdifference > 0) && (columndifference < 0)) {
        while ((rowdifference != 0) && (columndifference != 0)) {
            rowdifference--
            columndifference++
            y++
            x--
            way.add(Square(start.column + x, start.row + y))
            println(way)
        }
        while (rowdifference != 0) {
            rowdifference--
            y++
            way.add(Square(start.column + x, start.row + y))
            println(way)
        }
        while (columndifference != 0) {
            columndifference++
            x--
            way.add(Square(start.column + x, start.row + y))
            println(way)
        }
    }
    if ((rowdifference < 0) && (columndifference < 0)) {
        while ((rowdifference != 0) && (columndifference != 0)) {
            rowdifference++
            columndifference++
            y--
            x--
            way.add(Square(start.column + x, start.row + y))
        }
        while (rowdifference != 0) {
            rowdifference++
            y--
            way.add(Square(start.column + x, start.row + y))
        }
        while (columndifference != 0) {
            columndifference++
            x--
            way.add(Square(start.column + x, start.row + y))
        }
    }
    return way
}

/**
 * Сложная
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */
fun knightMoveNumber(start: Square, end: Square): Int = TODO()

/**
 * Очень сложная
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> = TODO()

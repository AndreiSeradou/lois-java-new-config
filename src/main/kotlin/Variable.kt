/*
    Лабораторная работа 1 по дисциплине ЛОИС
    Выполнена студентами группы 921703 БГУИР
    Завацкий Константин Анатольевич
    Вариант 23 - Реализовать прямой нечеткий логический вывод, используея импликацию Генцена
    14.12.2022
    Использованные материалы:
    "Прикладные нечеткие системы" Т.Тэрано, К.Асаи, М.Сугэно
    Методическое пособие по курсу "Логические основы интеллектуальных систем"
    Благодарность за помощь в реализации алгоритма выражается Валюкевичу В.И.
 */
class Variable(): Comparable<Variable> {
    var num = 0
    var ch = ""

    constructor(str: String) : this() {
        if (str.first() < 'a' || str.first() > 'z') {
            throw IllegalArgumentException()
        }

        ch = str.first().toString()
        val numStr = str.substring(1)
        num = if (numStr.isEmpty()) {
            -1
        } else {
            numStr.toInt()
        }
    }


    override fun toString(): String {
        return if (num != -1) {
            ch + num.toString()
        } else {
            ch + ""
        }
    }

    override fun compareTo(other: Variable): Int {
        return (ch + num).compareTo(other.ch + other.num)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Variable)
            return ch == other.ch
        return false
    }

    override fun hashCode(): Int {
        var result = num
        result = 31 * result + ch.hashCode()
        return result
    }
}

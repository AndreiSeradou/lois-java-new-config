/*
    Лабораторная работа 1 по дисциплине ЛОИС
    Выполнена студентами группы 921703 БГУИР
    Завацкий Константин Анатольевич
    Вариант 23 - Реализовать прямой нечеткий логический вывод, используея импликацию Лукасевича
    14.12.2022
    Использованные материалы:
    "Прикладные нечеткие системы" Т.Тэрано, К.Асаи, М.Сугэно
    Методическое пособие по курсу "Логические основы интеллектуальных систем"
    Благодарность за помощь в реализации алгоритма выражается Валюкевичу В.И.
 */
class SetElement(): Comparable<SetElement> {

    var variable = Variable()
    var value: Double = 0.0

    constructor(inVar: Variable, inVal: Double) : this() {
        variable = inVar
        value = inVal
    }

    constructor(string: String) : this() {
        var str = string
        if (str.first() != '(' || str.last() != ')') {
            throw IllegalArgumentException()
        }
        str = string.substring(1, str.length - 1)

        val i = str.indexOf(',')
        if (i < 0) throw IllegalArgumentException()
        variable = Variable(str.substring(0, i))

        value = str.substring(i + 1).toDouble()
        if (value < 0 || value > 1) {
            throw IllegalArgumentException()
        }
    }


    override fun toString(): String {
        return "($variable, $value)"
    }

    override fun compareTo(other: SetElement): Int {
        return other.variable.compareTo(other.variable)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SetElement)
            return variable == other.variable
        return false
    }

    override fun hashCode(): Int {
        var result = variable.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}

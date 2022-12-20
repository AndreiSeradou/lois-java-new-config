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
class MembershipDegreeSet() {

    val set = linkedSetOf<SetElement>()

    var name = ""

    constructor(names: ArrayList<String>, membershipDegree: ArrayList<Double>) : this() {
        if (names.size != membershipDegree.size) {
            throw java.lang.IllegalArgumentException()
        }
        names.forEachIndexed { i, _ ->
            val variable = Variable(names[i])
            set.add(SetElement(variable, membershipDegree[i]))
        }
    }

    constructor(string: String) : this() {
        if (string.first() != '{' || string.last() != '}') {
            throw IllegalArgumentException()
        }

        var str = string.substring(1, string.length - 1)

        while (true) {
            var i = str.indexOf(')')
            if (i < 0) {
                throw IllegalArgumentException()
            }
            i++

            set.add(SetElement(str.substring(0, i)))

            if (i >= str.length) {
                break
            }

            str = str.substring(i + 1)
        }
    }

    constructor(name: String, string: String) : this(string) {
        this.name = name
    }

    override fun toString(): String {
        val epsilon = 0.00001
        var str = "{"

        set.sortedBy { it.variable }.forEach {
            if (it.value > epsilon || it.value == 0.0) {
                str += "$it,"
            }
        }
        str = str.dropLast(1)
        if (str.isEmpty()) {
            str = "{"
        }
        return "$str}"
    }


    fun getElementsNames(): ArrayList<String> {
        val res = arrayListOf<String>()

        set.forEach {
            res.add(it.variable.toString())
        }
        res.sort()
        return res
    }
}

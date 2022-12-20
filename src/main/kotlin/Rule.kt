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


import kotlin.math.max
import kotlin.math.min


class Rule(val premise: MembershipDegreeSet, private val conclusion: MembershipDegreeSet) {
    private val values = arrayListOf<ArrayList<Double>>()

    init {
        premise.set.forEachIndexed { i, el1 ->
            values.add(i, arrayListOf())
            conclusion.set.forEachIndexed { j, el2 ->
                values[i].add(min(1.0, 1 - el1.value + el2.value))
            }
        }
    }

    fun applyTo(set: MembershipDegreeSet): MembershipDegreeSet {
        val answer = MembershipDegreeSet()

        conclusion.set.forEachIndexed { i, el2 ->
            var value = 0.0

            set.set.forEachIndexed { j, fromSet ->
                value = max(value, min(fromSet.value, values[j][i]))
            }

            answer.set.add(
                SetElement(
                    el2.variable, value
                )
            )
        }
        return answer
    }

    fun getHeader(): String {
        return premise.name + " -> " + conclusion.name
    }
}

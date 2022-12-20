import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    try {
        val sets = readSets()
        val rules = createRules(sets)
        printRules(rules)

        var input: String
        val amountOfRules = rules.size
        while (true) {

            println("Enter rule number")
            input = readLine() ?: ""

            try {
                val integer = input.toInt()
                if (integer <= 0 || integer > amountOfRules) {
                    throw IllegalArgumentException()
                }
                val rule = rules[integer - 1]
                println("You chose rule $integer: ${rule.getHeader()}")

                applyRule(rule)
            } catch (e: Exception) {
                println("You entered $input")
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        println("Invalid input")
    }
}

fun readSets(): ArrayList<MembershipDegreeSet> {
    val sets = ArrayList<MembershipDegreeSet>()
    val setNames = ArrayList<String>()
    var input: String
    val file = File(
        "C:\\Users\\a.seredov\\IdeaProjects\\untitled\\src\\main\\kotlin\\input"
    )
    val br = BufferedReader(FileReader(file))
    input = br.readLine() ?: ""
    while (input.isNotEmpty()) {
        val i = input.indexOf('=')
        if (i == -1) {
            throw java.lang.IllegalArgumentException()
        }

        if (input.first() < 'A' || input.first() > 'Z') {
            throw java.lang.IllegalArgumentException()
        }

        val setName = input.substring(0, i)
        if (setNames.find { it == setName } != null) {
            throw java.lang.IllegalArgumentException()
        }

        setNames.add(setName)
        sets.add(MembershipDegreeSet(setName, input.substring(i + 1)))

        input = br.readLine() ?: ""
    }
    return sets
}

fun createRules(sets: ArrayList<MembershipDegreeSet>): ArrayList<Rule> {
    val rules = arrayListOf<Rule>()
    sets.forEach { outerIter ->
        sets.forEach { innerIter ->
            if (outerIter != innerIter) {
                rules.add(Rule(outerIter, innerIter))
            }
        }
    }
    return rules
}

fun printRules(rules: ArrayList<Rule>) {
    var i = 1
    rules.forEach {
        println("$i\t${it.getHeader()}")
        i++
    }
}

fun applyRule(rule: Rule) {
    val premise = rule.premise
    val names = premise.getElementsNames()
    val membershipDegree = getMembershipDegreeForVariables(names)

    val enteredMembershipDegreeSet = MembershipDegreeSet(names, membershipDegree)
    val conclusion = rule.applyTo(enteredMembershipDegreeSet)
    println("result of applying the rule " + rule.getHeader() + " to\n" + enteredMembershipDegreeSet.toString() + "\nis\n" + conclusion.toString())
}

fun getMembershipDegreeForVariables(names: ArrayList<String>): ArrayList<Double> {
    val res = ArrayList<Double>()
    var input: String
    names.forEach {
        var isInputCorrect = false
        while (!isInputCorrect) {
            try {
                println("enter membership degree for $it")
                input = readLine() ?: ""
                if (input.indexOf(',') != -1 || input == "NaN") throw java.lang.IllegalArgumentException()
                val degree = input.toDouble()
                if (degree < 0 || degree > 1) {
                    throw java.lang.IllegalArgumentException()
                }
                res.add(degree)
                isInputCorrect = true
            } catch (e: java.lang.Exception) {
                println("Membership degree should be a number in range [0;1]")
            }
        }
    }
    return res
}
package com.example.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //functionType()
        //functionReference()
        //lambdaFunctionExample()
        //collections()
        //collectionConstruction()
        collectionOperations()
    }
}

fun collections() {

    // Collection
    //
    // immutable
    println("--> Collection")
    fun printAll(collection: Collection<String>) {
        for (s in collection) print("$s ")
        println()
    }

    fun printAll(collection: Collection<Int>) {
        for (i in collection) print("$i ")
        println()
    }

    var collection: Collection<String> // Collection<T> doesn't have constructor for instantiating object
    collection = listOf("one", "two", "three", "one")
    printAll(collection)

    collection = setOf("one", "two", "three")
    printAll(collection)

    // MutableCollection
    //
    // collection with additional write operations
    println("--> Mutable Collection")
    val mutableCollection = mutableListOf<String>() // mutableListOf<String>() used to declare mutableCollection as MutableList<String> type which is a subtype of MutableCollection
    mutableCollection.add("one")
    mutableCollection.add("two")
    mutableCollection.addAll(collection)
    printAll(mutableCollection)

    // List
    // default implementation of List is ArrayList
    println("--> List")
    val list = listOf(1, 2, 3, 4)
    //list.add(5);  give compile error
    printAll(list)
    println("element at 0: ${list.get(0)}")
    println("element at 1: ${list[1]}")
    println("index of elemen 3: ${list.indexOf(3)}")

    // two lists are equal if both have same size and elements with the same index
    val list2 = listOf(1, 2, 3, 4)
    val list3 = listOf(4, 3, 2, 1)
    println("list == list2? ${list == list2}") // true
    println("list == list3? ${list == list3}") // false

    // Initializer functions for lists
    val doubled = List(3) { it * 2}
    val tripled = MutableList(3) { it * 3}
    println(doubled) // print: [0, 2, 4]
    println(tripled)

    // Set
    // store unique elements. Order is undefined. Null elements are unique as well.
    // default implementation of setOf() is LinkedHashSet
    // LinkedHashSet preserves the order of elements insertion. Functions that rely on first() or last()
    // return predictable results on such sets
    println("--> Set")
    val numberSet = setOf(1, 2, 4, 3, 4)
    println("size of numberSet: ${numberSet.size}") // size = 4
    printAll(numberSet) // the last "4" not printed.
    val numberSet2 = setOf(4, 4, 3, 2, 1)
    val numberSet3 = setOf(4, 3, 2, 1)
    val numberSet4 = setOf(4, 3, 2)
    val numberSet5 = setOf(1, 2, 4, 3, 4, 5) // the "4" after "3" will be ignored
    println("numberSet is equal numberSet2? ${numberSet == numberSet2}") // true
    println("numberSet is equal numberSet3? ${numberSet == numberSet3}") // true
    println("numberSet is equal numberSet4? ${numberSet == numberSet4}") // false
    println("first element of numberSet: ${numberSet.first()}") // 1
    println("last element of numberSet: ${numberSet.last()}") // 3
    println("first element of numberSet5: ${numberSet5.first()}") // 1
    println("last element of numberSet5: ${numberSet5.last()}") // 5

    println("--> MutableSet")
    val alphabetSet = mutableSetOf("a", "b", "c")
    alphabetSet.add("d")
    alphabetSet.add("e")
    printAll(alphabetSet)
    alphabetSet.remove("d")
    printAll(alphabetSet)

    // order is not preserved. HashSet requires less memory to store the same number of elements
    println("--> HashSet")
    val hashSet = HashSet<Int>()
    hashSet.add(1)
    hashSet.add(2)
    printAll(hashSet)
    hashSet.addAll(numberSet) // preserve unique elements in hashSet
    printAll(hashSet)

    // Map<K, V>
    // stores key-value pairs. Keys are unique, but different keys can be pairs with equal values
    // default implementation of Map is LinkedHashMap which preserves the order of elements insertion when
    // iterating the map. In turn, HashMap says nothing about the element order
    println("--> Map<K, V>")
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 4)
    println("All keys: ${numbersMap.keys}")
    println("All values: ${numbersMap.values}")
    if ("key2" in numbersMap) println("Value by key \"key2\": ${numbersMap["key2"]}")
    if (1 in numbersMap.values) println("The value 1 is in the map")
    if (numbersMap.containsValue(1)) println("The value 1 is in the map")

    // two maps are equal if contain equal pairs regardless of the pair order
    val anotherMap = mapOf("key2" to 2, "key3" to 3, "key1" to 1, "key4" to 4)
    println("The maps are equal: ${numbersMap == anotherMap}")

    // non-exhaustive memory usage Map declaration is using apply
    val numbersMapApply = mutableMapOf<String, String>().apply { this["key1"] = "1"; this["key2"] = "2" }
    println("All keys numbersMapApply: ${numbersMapApply.keys}")
    println("All values numbersMapApply: ${numbersMapApply.values}")
}

fun collectionConstruction() {

    // Copying
    // create shallow copying operations.
    // example method: toList(), toMutableList(), toSet()
    println("--> Copying")
    var sourceList = mutableListOf(1, 2, 3)
    val copyList = sourceList.toMutableList() // converting collections to other types
    val readOnlyCopyList = sourceList.toList()
    sourceList.add(4)
    println("Copy size: ${copyList.size}")
    //readOnlyCopyList.add(4) // compilation error
    println("Read-only copy size: ${readOnlyCopyList.size}")

    // new references to the same collection.
    // When the collection instance is altered through a reference, the changes
    // are reflected in all its references
    println("--> Reference")
    val referenceList = sourceList
    referenceList.add(4)
    println("Source size: ${sourceList.size}")
}

fun collectionOperations() {

    // Extension Functions of collection : filtering, transformation, ordering, etc.
    //
    // Invoking functions on other collections
    // creating a new collection by applying various operations on other collections
    println("Invoking functions on other collections")

    // filtering -> create a list that match the filter
    println("-> filtering")
    var sourceList = mutableListOf(1, 2, 3, 4, 5, 6, 7)
    val evenList = sourceList.filter { it % 2 == 0 }
    println(evenList)

    // mapping -> produces a list of transformation results
    println("-> mapping")
    val numbersSource = sourceList.toSet()
    println(numbersSource.map { it * 3 })
    println(numbersSource.mapIndexed { idx, value -> value * idx})

    // association produces maps
    println("-> association")
    println(numbersSource.associateWith { it % 2 == 0 })

    // If any two elements are equal, the last one gets added to the map.
    sourceList = mutableListOf(1, 2, 3, 4, 3, 2, 1)
    println(sourceList.associateWith { it % 2 == 0 }) // {1=false, 2=true, 3=false, 4=true}

    // groupingBy -> creates a grouping source from an array to be used later
    //               with one of group-and-fold operations using the specified
    //               keySelector function to extract a key from each element
    println("-> GroupingBy")
    println("definition: inline fun <T, K> Array<out T>.groupingBy(\n" +
            "    crossinline keySelector: (T) -> K\n" +
            "): Grouping<T, K>")
    val words = "one two three four five six seven eight nine ten".split(' ')
    val frequenciesByFirstChar = words.groupingBy { it.first() }.eachCount()
    println("Counting first letters: ")
    println(frequenciesByFirstChar)

    // filterTo -> put all items specified by filterTo predicate to the destination of mutable collection type
    println("-> filterTo")
    var oddList = mutableListOf<Int>() // destination
    sourceList.filterTo(oddList) { it % 2 != 0}
    println(oddList)

    // oddList will get additional items when following lines get executed
    sourceList.filterIndexedTo(oddList) { index, _ -> index % 2 == 0}
    println(oddList)

    // mapTo -> these function return the destination collection back

    // this example will filter numbers right into a hash set,
    // thus eliminating duplicates in the result
    val numbers = listOf("one", "two", "three", "four")
    val result = numbers.mapTo(HashSet()) { it.length }
    println("distinct item lengths are $result")

}
fun functionType() {
    /**
     * Function Type
     *
     * syntactic sugar for an interface, but the interface cannot be used explicitly
     * */

    // function type () -> Unit denote that invoke takes no argument and return void
    class MyFunction: () -> Unit {
        override fun invoke() {
            println("I am called")
        }
    }

    val function = MyFunction()
    function() // prints: I am called

    // function type (Int) -> Int
    class MyFunction2: (Int) -> Int {
        override fun invoke(p1: Int): Int {
            return p1 * p1
        }
    }

    val function2 = MyFunction2()
    println("square result: ${function2(4)}")

    // function type as type local variables with no value
    val greet: () -> Unit
    val square: (Int) -> Int
    val producePrinter: () -> () -> Unit // function type that returns another function that
                                         // returns nothing useful (Unit). Both functions take no arguments
}

fun functionReference() {

    /**
     * Function reference
     *
     * referencing to the actual function. Function reference is an example of reflection
     * It returns reference to the function which also implements an interface
     * that represent function type
     * */
    fun greetFunction() {
        println("Hello")
    }
    val greet: () -> Unit = ::greetFunction
    println("greet: $greet")
}

fun lambdaFunctionExample() {

    /**
     * Example
     *
     * @param (String s)
     * @return (Unit) "void"
     * @format var/val lambdaName : Type = { argumentList -> body }
     *
     * The only part of a lambda which isn't optional is the codeBody.
     * The argument list can be skipped when defining at most one argument and the Type can often be inferred by the Kotlin compiler.
     * We don't always need a variable as well, the lambda can be passed directly as a method argument
     * The type of the last command within a lambda block is the returned type.
     * */
    var link: (String) -> Unit = { s: String -> println(s) }
    var link2: (String) -> Unit = { s -> println(s) } // example of skipped argument list
    perform("This is an input string", link)
    perform("This is an input string", link2)

    /**
     * Type inference
     *
     * Kotlin's type inference allows the type of a lambda to be evaluated by the compiler.
     * Writing a lambda that produces the square of a number would be as written as:
     * */
    val square = { number: Int -> number * number }
    val nine = square(3) // function that takes one Int and returns an Int: (Int) -> Int
    // the "(Int) -> Int)" comes from square lambda function
    println("square = $nine")

    // If we wanted to create a lambda that multiplies its single argument numbers by 100 then
    // returns that value as a String
    val magnitude100String = { input: Int ->
        val magnitude = input * 100
        magnitude.toString() // Kotlin will understand that this lambda is of type (Int) -> String
    }
    val multiplicationof100 = magnitude100String(5)
    println("magintude of 100: $multiplicationof100")

    /**
     * Type Declaration
     *
     * Occasionally Kotlin can't infer our types and we must explicitly declare the type for our lambda;
     * just as we can with any other type.
     * The pattern is input -> output, however, if the code returns no value we use the type Unit
     *
     * val that : Int -> Int = { three -> three }
     * val more : (String, Int) -> String = { str, int -> str + int }
     * val noReturn : Int -> Unit = { num -> println(num) }
     *
     * */
    val divideByTwo: (Int) -> Int = { number: Int ->
        number / 2
    }
    val more: (String, Int) -> String = { s: String, n: Int -> s + n }
    val noReturn: (Int) -> Unit = { inputNumber: Int -> println(inputNumber) }

    println("divide by two: ${divideByTwo(4)}")
    println("more: ${more("4", 2)}")
    noReturn(3)

    /** Extension Functions
     *
     * adding new function as method function to an object
     * */
    fun appendTag(string: String): String {
        return StringBuilder().append("<")
            .append(string)
            .append(">")
            .toString()
    }

    val A = "Rangga"
    val B = "Bagas"

    println("Non-extension function")
    println("appendtag A: ${appendTag(A)}")
    println("appendtag B: ${appendTag(B)}")

    fun String.appendTagExtension() {
        StringBuilder().append("<")
            .append(this)
            .append(">")
            .toString()
    }

    println("Extension function")
    println("A.appendtag(): ${A.appendTagExtension()}")
    println("B.appendtag(): ${B.appendTagExtension()}")


    /**
     * Function Literals with a Receiver. Lambda as class extension
     * pattern -> Type.lambdaName(arguments)
     *
     * extend means that the object get additional method
     * */
    println("lambda extension: ${extendString("test", 3)}")
    println("lambda extension 2: ${extendInt(5, " is number ")}")
    println(square(5))
    println(squared(5) { it * 2 })
    println(squared2(5) { this * 2 })

    /** Higher Order Function Literals
     *
     * Functions that take other functions as input or output are called Higher Order Function, aka HOF.
     * */
    val squareFunctionLiteral: (Int) -> Int = { it * it }
    println("square function literal: ${squareFunctionLiteral(3)}")

    val apply5: ((Int) -> Int) -> Int = { it(5) }
    println("apply5: " + apply5 { it * it })

    val applySum: (Int) -> ((Int) -> Int) = { x -> { it + x } }
    println("applySum: " + applySum(4)(7))

    val applyInverse: ((Int) -> Int) -> ((Int) -> Int) = { f -> { -1 * f(it) } }
    println("applyInverse: " + applyInverse { it * it }(5))

    // this function type is a "function type" which take "function type (Int) -> Int as input" and function type (Int) -> Boolean as output
    // f(it) = it * 5, so f(6) = 6 * 5 = 30
    val applyMultiplicationIsEven: ((Int) -> Int) -> ((Int) -> Boolean) =
        { f -> { f(it) % 2 == 0 } }
    println("apply multiplication is even? " + applyMultiplicationIsEven { it * 5 }(6))

    val applyIsEven: (Boolean) -> String = { it -> if (it) "genap" else "ganjil" }
    println("apply is even: " + applyIsEven(applyMultiplicationIsEven { it * 5 }(6)))

    /**
     * - buildString takes a function "action" as a parameter
     * - action function takes StringBuilder (builder) as a parameter which returns Unit
     * - buildString returns string as output
     * */
    fun buildString(action: (StringBuilder) -> Unit): String{
        val builder = StringBuilder()
        action(builder)
        return builder.toString()
    }

    /**
     * The receiver is used in kotlin to access the property of the receiver type
     * without any additional line of code or qualifier
     *
     * below function declaration is equivalent with:
     *
     * buildString (fun someFunctionName(builder:StringBuilder) {
     *      builder.append("<")
     *      builder.append("MindOrks")
     *      builder.append(">")
     *      })
     * */
    var resultBuildString = buildString { builder -> // builder is a receiver
        builder.append("<")
        builder.append("Mindorks")
        builder.append(">")
    }
    println("result build string: $resultBuildString")

    resultBuildString = buildString {
        it.append("<")
        it.append("Mindorks")
        it.append(">")
    }
    println("result build string: $resultBuildString")

    // convert buildString to use extensionFunction
    // here, StringBuilder (as receiver) added with extension function to access its properties directly
    // The extention function has type "() -> Unit"
    fun buildStringWithExtensionFunction(action: (StringBuilder).() -> Unit): String {
        val stringBuilder = StringBuilder()
        action(stringBuilder)
        return stringBuilder.toString()
    }

    // by using extension function, we could access object properties or methods directly
    resultBuildString = buildStringWithExtensionFunction {
        append("<")
        append("Mindorks")
        append(">")
        this.append("end")
    }
    println("result build string: $resultBuildString")

    // define the print function here
    println("print 5: ${print5 { "The number is $it" }}") // The number is 5
    println("print 6: ${print6(6) { "The new number is $it" }}") // 6The new number is 5
    println("print 7: ${print7(7) { "$it is the new number" }}")


    /**
     * Returning from lambda
     *
     * The final expression is the value that will be
     * returned after a lambda is executed
     * */
    var calculateGrade = { grade: Int ->
        when (grade) {
            in 0..40 -> "Fail"
            in 41..70 -> "Pass"
            in 71..100 -> "Distinction"
            else -> false
        }
    }
    println("the retuned value of calculateGrade: ${calculateGrade(50)}")

    // using anonymous function
    calculateGrade = fun(grade: Int): String {
        if (grade < 0 || grade > 100) return "Error"
        else if (grade < 40) return "Fail"
        else if (grade < 70) return "Pass"
        return "Distinction"
    }
    println("calculate grade now: ${calculateGrade(60)}")

    /**
     * it
     *
     * A shorthand of a single argument lambda is to use the keyword ‘it'.
     * This value represents any lone that argument we pass to the lambda function
     * */
    val array = arrayOf(1, 2, 3, 4, 5, 6)

    println("longhand form: ")
    array.forEach { item -> print(item * 4) } // longhand form

    println("\nshorthand form: ")
    array.forEach { print(it * 4) }

    /**
     * Change outside variable inside lambda body
     *
     * in Java 8, it's immutable. Whereas in Kotlin it's mutable
     * */
    var outsideVariable = 0
    addTwoNumbers(2, 7) { x, y -> outsideVariable = x + y}
    println("outside variable: $outsideVariable")

    /**
     *
     * Five options passing lambda as an argument into higher-order method
     * 1. lambda object variable */
    val lambda1 = { arg: Double -> arg == 4.329 }
    println("lambda1: ${lambda1(4.329)}")


}

fun addTwoNumbers(a: Int, b: Int, action: (Int, Int) -> Unit) {
    action(a, b)
}

fun perform(input: String, linking: (String) -> Unit) {
    linking(input)
}

fun extendString(arg: String, num: Int) : String {
    val another: String.(Int) -> String = { this + it }
    return arg.another(num)
}

fun extendInt(arg: Int, value: String) : String {
    val another: Int.(String) -> String = { it + (this * 2).toString() } // it = string value, this = integer object
    return arg.another(value)
}

// function literals with receiver functions declaration
fun square(x: Int): Int = x * x
fun squared(a: Int, f: (Int) -> Int): Int = f(square(a))
fun squared2(a: Int, f: Int.() -> Int): Int = f(square(a))

// declare first, define later
fun print5(functionArg: (Int) -> String) = functionArg(5)
fun print6(n: Int, anotherFunctionArg: (Int) -> String) : String {
    return n.toString() + print5(anotherFunctionArg)
}
fun print7(n: Int, func: (Int) -> String) : String = func(n)
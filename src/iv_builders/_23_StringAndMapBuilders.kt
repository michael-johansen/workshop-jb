package iv_builders

import util.TODO
import java.util.*

fun buildString(build: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.build()
    return stringBuilder.toString()
}

fun buildStringExample(): String {
    return buildString {
        this.append("Numbers: ")
        for (i in 1..10) {
            // 'this' can be omitted
            append(i)
        }
    }
}

fun todoTask23() = TODO(
    """
        Task 23.
        Uncomment the commented code and make it compile.
        Add and implement function 'buildMap' with one parameter (of type extension function) creating a new HashMap,
        building it and returning it as a result.
        Use MutableMap; look through the syntax/javaCollections.kt file for details.
    """,
    references = { syntax.javaCollections.useMutableSet(HashSet())}
)

fun task23(): Map<Int, String> {
//    todoTask23()
    return buildMap {
        this.put(0, "0")
        for (i in 1..10) {
            this.put(i, "$i")
        }
    }
}

fun buildMap(buildMap: MutableMap<Int, String>.() -> Unit): Map<Int, String> {
    val map = HashMap<Int, String>()
    map.buildMap()
    return map
}

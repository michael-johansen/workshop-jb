package ii_conventions

import util.TODO

interface My {
    fun invoke(i: Int)
}

// Objects with 'invoke' function can by invokable as a function
fun testTypeWithInvokeMember(my: My) {
    my(1)

    // the same as
    my.invoke(1)
}

fun testFunctionType(f: (Int) -> Int) {
    // Actually, the 'invoke' member is declared in a function type
    f.invoke(1)
    f(1)
}

// Press 'Ctrl + B' on round bracket to navigate to 'invoke'

// You can add an 'invoke' extension for any class,
// but it's better not to overdo it
fun Int.invoke() { println(this) }

fun testTypeWithInvokeExtension() {
    1() //huh?..
}

class Invokable(var invocations: Int = 0){
    fun invoke():Invokable {
        return Invokable(invocations + 1)
    }

    fun getNumberOfInvocations(): Int = invocations
}

fun todoTask17() = TODO(
    """
        Task 17.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task17(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}

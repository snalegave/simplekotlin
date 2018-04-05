// Explore a simple class

println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String

fun whenFn (x: Any):String {
    when (x){
        "Hello"-> return("world")
        "Howdy"-> return("Say what?")
        "Bonjour" -> return("Say what?")
        0 -> return("zero")
        1 -> return("one")
        5 -> return("low number")
        9 -> return("low number")
        else -> return("I don't understand" )
    }   
}


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add (x: Int, y: Int): Int{
    return (x+y)
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub (x: Int, y: Int):Int{
    return (x-y)
}
// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(x: Int, y: Int, oper:(x: Int, y:Int)-> Int):Int{
    return(oper(x,y))
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, age: Int){
    var age: Int = age
    get() = field
    set(value){
        field = value
    }

    val debugString = "[Person firstName:${firstName} lastName:${lastName} age:${age}]"
    // fun verifyAge(age: Int): Int {
    //     if (this.age < 0) {
    //         throw Exception("age cannot be below 0")
    //     } else {
    //         return age
    //     }
    // }
}

// write a class "Money"
class Money(amountArg: Int, currencyArg: String){
    val currencyList = listOf("USD", "EUR", "CAN", "GBP")
    val currencyRates = listOf(
        Pair("USD", 10) to Pair("GBP", 5),
        Pair("USD", 10) to Pair("EUR", 15),
        Pair("USD", 12) to Pair("CAN", 15),
        Pair("GBP", 5) to Pair("USD", 10),
        Pair("EUR", 15) to Pair("USD", 10),
        Pair("CAN", 15) to Pair("USD", 12)
    )

    var amount = verifyAmount(amountArg) 
        get() = field
        set(value){
            field = verifyAmount(value)
        } 
        

    var currency = verifyCurrency(currencyArg)
        get() = field
        set(value){
            field = verifyCurrency(value)
        }

    fun verifyAmount(amount: Int): Int {
        if (this.amount < 0) {
            throw Exception("Amount cannot be below 0")
        } else {
            return amount
        }
    }

    fun verifyCurrency(currency: String): String {
        if (currencyList.contains(currency)) {
            return currency
        } else {
            throw Exception("Invalid currency")
        }
    }

    fun convert (convertTo: String): Money{                    //check if the convert to is the same as the current currency
        var newMoney = Money(this.amount, this.currency) 
        if(this.currency != convertTo){
            var x:Int = this.amount
            var xDollars:Int = this.amount

            if(this.currency != "USD"){
                for((k,v)in currencyRates){
                    if(k.first == this.currency){
                        xDollars = x * v.second / k.second
                    }
                }
            }
            if(convertTo == "USD"){

                newMoney.amount = xDollars;
                newMoney.currency = "USD"

            } else {
                for((k,v)in currencyRates){
                    if(k.first == "USD" && v.first == convertTo){
                        x = xDollars * v.second / k.second
                    }
                }
                newMoney.amount = x;
                newMoney.currency = convertTo

            }
        }
        return (newMoney)
    }

    operator fun plus (otherCur: Money): Money{
        val firstCurrency:String = this.currency
        var newMoney = Money(this.amount, firstCurrency)
        if (otherCur.currency == firstCurrency){
            newMoney.amount = this.amount+ otherCur.amount
        } else {
            newMoney.amount = this.amount + otherCur.convert(firstCurrency).amount /*other.convert(firstCurrency).amount*/
        }
        return newMoney
    }
}


// ============ DO NOT EDIT BELOW THIS LINE =============
print("When tests: ")
val when_tests = listOf(
    "Hello" to "world",
    "Howdy" to "Say what?",
    "Bonjour" to "Say what?",
    0 to "zero",
    1 to "one",
    5 to "low number",
    9 to "low number",
    17.0 to "I don't understand"
)
for ((k,v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
    Pair(0, 0) to 0,
    Pair(1, 2) to 3,
    Pair(-2, 2) to 0,
    Pair(123, 456) to 579
)
for ( (k,v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
    Pair(0, 0) to 0,
    Pair(2, 1) to 1,
    Pair(-2, 2) to -4,
    Pair(456, 123) to 333
)
for ( (k,v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l,r -> l+r} ) == 4) "." else "!")
print(if (mathOp(2, 2, ::add ) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub ) == 0) "." else "!")
print(if (mathOp(2, 2, { l,r -> l*r} ) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "! ${p1.debugString}")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
    Pair(tenUSD, tenUSD),
    Pair(tenUSD, fiveGBP),
    Pair(tenUSD, fifteenEUR),
    Pair(twelveUSD, fifteenCAN),
    Pair(fiveGBP, tenUSD),
    Pair(fiveGBP, fifteenEUR)
)
for ( (from,to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
    Pair(tenUSD, tenUSD) to Money(20, "USD"),
    Pair(tenUSD, fiveGBP) to Money(20, "USD"),
    Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ( (pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
              (pair.first + pair.second).currency == result.currency) "." else "! ${pair.first.currency}")
}
println("")
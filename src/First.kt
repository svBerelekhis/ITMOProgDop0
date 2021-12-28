data class Cat(val color: String, val age: Int, val weight: Int)
val names : Array<String?> = arrayOfNulls<String>(1000)
val cats : Array<Cat?> = arrayOfNulls<Cat>(1000)

fun main(){
    var nowLong = 0
    while(true){
        val command = readLine()
        val comm = command!!.substringBefore(" ")
        val last = command.substringAfter(" ")
        when (comm) {
            "create" -> {
                val masLast = last.split(" ").toTypedArray()
                val name = masLast[0]
                val color = masLast[1]
                val age = masLast[2].toInt()
                val weight = masLast[3].toInt()
                val cat = Cat(color, age, weight)
                nowLong = create(name, cat, nowLong)
                println("$name->$cat")
            }
            "readall" -> {
                readAll(nowLong)
            }
            "read" -> {
                val cat = readId(last, nowLong)
                println("$last->$cat")
            }
            "delete" -> {
                nowLong = delete(last, nowLong)
                println("OK")
            }
        }
    }

}
fun create(name : String, cat : Cat, nowLong : Int) : Int{
    names[nowLong] = name
    cats[nowLong] = cat
    return nowLong + 1
}
fun delete(name: String, nowLong: Int) : Int{
    var i = 0
    while (i < nowLong && names[i] != name){
        i+= 1
    }
    while (i + 1 < nowLong){
        names[i] = names[i + 1]
        cats[i] = cats[i + 1]
    }
    return nowLong - 1
}
fun readAll(nowLong: Int) {
    val range = 0 until nowLong
    for(i in range){
        println(names[i] + "->" + cats[i])
    }
}
fun readId(name : String, nowLong : Int): Cat? {
    var i = 0
    while (i < nowLong && names[i] != name){
        i += 1
    }
    return cats[i]
}

data class CatH(val color: String, val age: Int, val weight: Float)
data class NodeH(val name : String, val cat : CatH, var prev : NodeH?, var next : NodeH?)
data class ListOfNodeH(var first : NodeH?, var last : NodeH?)
val hashTable =  arrayOfNulls<ListOfNodeH>(10000)

fun main(){
    for (i in 0 until 10000){
        hashTable[i] = ListOfNodeH(null, null)
    }
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
                val weight = masLast[3].toFloat()
                createH(name, color, age, weight)
            }
            "readall" -> {
                readAllH()
            }
            "read" -> {
                readH(last)
            }
            "delete" -> {
                deleteH(last)
            }
            "update" -> {
                val masLast = last.split(" ").toTypedArray()
                val name = masLast[0]
                val color = masLast[1]
                val age = masLast[2].toInt()
                val weight = masLast[3].toFloat()
                updateH(name, color, age, weight)
            }
            "where" -> {
                val weight = last.substringAfter("=").toFloat()
                whenWhere(weight)
            }
        }
    }
}
fun updateH (name : String, color : String, age : Int, weight: Float) {
    val num = hashFun(name)
    val lON = hashTable[num]
    var now = lON?.first
    while(now!= lON?.last && now!!.name != name){
        now = now.next
    }
    if (now!!.name != name){
        println("Update: not found")
    }else {
        val newCat = CatH(color, age, weight)
        val newNode = NodeH(name, newCat, now.prev, now.next)
        if (now.prev != null){
            now.prev!!.next = newNode
        }else {
            lON?.first = newNode
        }
        if (now.next != null){
            now.next!!.prev = newNode
        } else {
            lON!!.last = newNode
        }
        println(name + "->" + newNode.cat)
    }
}



fun createH(name: String, color: String, age: Int, weight: Float){
    val num = hashFun(name)
    val lON = hashTable[num]
    val cat = CatH(color, age, weight)
    println(num)
    if (lON!!.first == null) {
        val node = NodeH(name, cat, null, null)
        lON.first = node
        lON.last = node
        println("$name->$cat")
    }else {
        var now = lON.first
        while(now != lON.last && name != now!!.name){
            now = now.next
        }
        if (name == now!!.name){
            println("Create: already exists")
        } else {
            val ch = lON.last
            val node = NodeH(name, cat, ch, null)
            ch!!.next = node
            lON.last = node
            println("$name->$cat")
        }
    }
    hashTable[num] = lON
}
fun readH(name : String){
    val num = hashFun(name)
    val lON = hashTable[num]
    var now = lON?.first
    while(now!= lON?.last && now!!.name != name){
        now = now.next
    }
    if (now!!.name != name){
        println("Read: not found")
    }else {
        println(name + "->" + now.cat)
    }
}
fun deleteH(name: String){
    val num = hashFun(name)
    val lON = hashTable[num]
    var now = lON!!.first
    while (now != lON.last && now!!.name != name){
        now = now.next
    }
    if (now!!.name != name){
        println("Delete: not found")
    } else {
        println(now.name + "->" + now.cat)
        if (now.prev != null){
            now.prev!!.next = now.next
            if(now.next != null){
                now.next!!.prev = now.prev
            }else {
                lON.last = now.prev
            }
        }else {
            lON.first = now.next
            if (now.next != null){
                now.next!!.prev = null
            }
        }
    }
    hashTable[num] = lON
}
fun readAllH() {
    for (i in 0 until 1000) {
        val lON = hashTable[i]
        var now = lON?.first
        while (now != null) {
            println(now.name + "->" + now.cat)
            now = now.next
        }
    }
}

fun whenWhere(weight: Float){
    for (i in 0 until 1000) {
        val lON = hashTable[i]
        var now = lON?.first
        while (now != null) {
            if (kotlin.math.abs(now.cat.weight - weight) <= 0.001){
                println(now.name + "->" + now.cat)
            }
            now = now.next
        }
    }
}
fun hashFun(name : String): Int{
    return(name.hashCode()%1000)
}
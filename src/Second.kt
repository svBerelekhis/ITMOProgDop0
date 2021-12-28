data class Catt(val color: String, val age: Int, val weight: Float)
data class Node(val name : String, val cat : Catt, var prev : Node?, var next : Node?)
data class ListOfNode(var first : Node?, var last : Node?)
val listOfNode = ListOfNode(null, null)

fun main(){
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
                createD(name, color, age, weight)
            }
            "readall" -> {
                readAllD()
            }
            "read" -> {
                readD(last)
            }
            "delete" -> {
                deleteD(last)
            }
        }
    }

}

fun createD(name: String, color: String, age: Int, weight: Float){
    val cat = Catt(color, age, weight)
    if (listOfNode.first == null) {
        val node = Node(name, cat, null, null)
        listOfNode.first = node
        listOfNode.last = node
        println("$name->$cat")
    }else {
        var now = listOfNode.first
        while(now != listOfNode.last && name != now!!.name){
            now = now.next
        }
        if (name == now!!.name){
            println("Create: already exists")
        } else {
            val ch = listOfNode.last
            val node = Node(name, cat, ch, null)
            ch!!.next = node
            listOfNode.last = node
            println("$name->$cat")
        }
    }
}
fun readD(name : String){
    var now = listOfNode.first
    while(now!=listOfNode.last && now!!.name != name){
        now = now.next
    }
    if (now!!.name != name){
        println("Read: not found")
    }else {
        println(name + "->" + now.cat)
    }
}
fun deleteD(name: String){
    var now = listOfNode.first
    if (now == null){
        println("Delete: not found")
        return
    }
    while (now != listOfNode.last && now!!.name != name){
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
                listOfNode.last = now.prev
            }
        }else {
            listOfNode.first = now.next
            if (now.next != null){
                now.next!!.prev = null
            }
        }
    }
}
fun readAllD() {
    var now = listOfNode.first
    while (now != null){
        println(now.name + "->" + now.cat)
        now = now.next
    }
}
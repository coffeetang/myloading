import java.lang.StringBuilder

inline fun String.ABC(turn:(Int)-> String):String{
    val res = StringBuilder()
    indices.forEach {
        res.append(turn(it))
        res.append(get(it).uppercase())
    }
    return res.toString()
}

fun main(){
    "saskkjds".ABC {
        "${it}."
    }
}
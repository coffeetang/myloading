package loading

class Meat{
    fun printMeat(){
        println("吃肉肉")
    }
}
fun furtherScope(meat:Any){
    val isMeat = meat is Meat
//    if(isMeat){
//        meat.printMeat()
//    }
}

//fun main(){
//    val meat = Meat()
//    furtherScope(meat)
//}
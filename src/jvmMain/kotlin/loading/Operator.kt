package loading

open class AAA{
    fun callAAA(){
        println("AAA")
    }
}
class AAAOne:AAA()
class AAATwo:AAA()

fun checkType(aaa:Any){
    if(aaa is AAAOne || aaa is AAATwo){
        println(aaa.javaClass.name)
    }
}

fun main(){

    checkType(AAAOne())
}
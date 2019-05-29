package sample

fun hello(): String = "Hello from Kotlin"

class Proxy {
    fun proxyHello() = hello()
}

fun main() {
    println(hello())
}
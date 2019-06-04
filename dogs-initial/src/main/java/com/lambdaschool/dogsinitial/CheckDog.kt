package com.lambdaschool.dogsinitial

interface CheckDog {
    fun test(d: Dog): Boolean

    companion object {
        inline operator fun invoke(crossinline op: (d: Dog) -> Boolean) =
                object  : CheckDog {
                    override fun test(d: Dog): Boolean = op(d)
                }
    }
}
package com.example.fundamentosjunit

import org.junit.Assert.*
import org.junit.Test

class AssertionsTest {
    @Test
    fun getArrayTest(){
        val assertions = Assertions()
        val array = arrayOf(22, 44) // Valor esperado
        assertArrayEquals(array, assertions.getLuckyNumbers())
    }
}
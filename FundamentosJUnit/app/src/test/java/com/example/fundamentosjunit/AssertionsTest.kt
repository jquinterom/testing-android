package com.example.fundamentosjunit

import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

class AssertionsTest {
    @Test
    fun getArrayTest() {
        val assertions = Assertions()
        val array = arrayOf(22, 44) // Valor esperado
        // val array = arrayOf(22, 4) // Valor esperado erroneo
        assertArrayEquals(
            "Mensaje personalizado de error en testing",
            array,
            assertions.getLuckyNumbers()
        )
    }

    @Test
    fun getNameTest() {
        val assertions = Assertions()
        val name = "Jhon"
        val otherName = "MA"
        assertEquals(name, assertions.getName())
        assertNotEquals(otherName, assertions.getName())
    }

    @Test
    fun checkHumanTest() {
        val assertions = Assertions()
        val bot = User("8bit", 1, false)
        val juan = User("Juan", 18, true)

        // OK
        // assertFalse(assertions.checkHuman(bot))
        // assertTrue(assertions.checkHuman(juan))

        // ERROR
        assertFalse(assertions.checkHuman(juan))
        assertTrue(assertions.checkHuman(bot))
    }


    @Test
    fun checkNullUserTest() {
        val user = null
        val assertions = Assertions()

        assertNull(user)
        assertNull(assertions.checkHuman(user))
    }

    @Test
    fun checkNotNullUserTest() {
        val user = User("Juan", 18, true)
        assertNotNull(user)
    }

    @Test
    fun checkNotSameUsersTest() {
        val bot = User("8bit", 1, false)
        val juan = User("Juan", 18, true)
        assertNotSame(bot, juan)
    }

    @Test
    fun checkSameUsersTest() {
        val bot = User("Juan", 18, true)
        val juan = User("Juan", 18, true)
        val copyJuan = juan
        assertSame(copyJuan, juan)
    }

    @Test(timeout = 1_000)
    fun getCitiesTest(){
        val cities = arrayOf("Colombia", "Perú", "México")
        Thread.sleep(Random.nextLong(200, 1_100))
        // Thread.sleep(Random.nextLong(950, 1_050))
        assertEquals(3, cities.size)
    }
}
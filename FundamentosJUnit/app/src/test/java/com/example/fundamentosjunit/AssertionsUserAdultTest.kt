package com.example.fundamentosjunit

import org.junit.*
import org.junit.Assert.*

class AssertionsUserAdultTest {
    private lateinit var bot: User
    private lateinit var juan: User

    @get: Rule val locationCORule = LocationCORule()

    @Before
    fun setup() {
        juan = User("Juan", 18, true)
        bot = User("8bit", 1, false)
    }

    @After
    fun tearDown() {
        juan = User("Juan", 18, true)
        // limpiar variables
        bot = User()
    }


    @Test
    fun isAdultTest() {
        /*
        val assertions = Assertions()
        assertions.setLocation("CO")
        assertTrue(assertions.isAdult(juan))
        assertTrue(assertions.isAdult(bot))
        */

        assertEquals(true, locationCORule.assertions?.isAdult(juan))
        assertEquals(true, locationCORule.assertions?.isAdult(bot))
    }
}
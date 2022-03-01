package com.example.fundamentosjunit

import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(value = Parameterized::class)
class ParameterizedTest(var currentValue: Boolean, var currentUser: User) {

    @get: Rule
    val locationCORule = LocationCORule()

    companion object {
        var assertions: Assertions? = null

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            assertions = Assertions()
        }

        @AfterClass
        @JvmStatic
        fun tearDownCommon() {
            assertions = null
        }

        @Parameterized.Parameters
        @JvmStatic
                /*
                fun getUsersUS() =
                    arrayOf(
                        arrayOf(false, User("Jhon", 11)),
                        arrayOf(true, User("Martha", 34)),
                        arrayOf(true, User("Bot3", 4, false)),
                        arrayOf(false, User("Alex", 18)),
                    )
                */


        fun getUsersCO() =
            arrayOf(
                arrayOf(true, User("Jhon", 19)),
                arrayOf(false, User("Martha", 14)),
                arrayOf(true, User("Bot3", 4, false)),
                arrayOf(true, User("Alex", 18)),
            )
    }

    @Test
    fun isAdultTest() {
        assertEquals(currentValue, locationCORule.assertions?.isAdult(currentUser)) // regla
        // assertEquals(currentValue, assertions?.isAdult(currentUser))
    }
}
package com.cursosandroidant.productviewer

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest // Indicarque la prueba será grande
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun setNewQuantity_sum_increasesTextField() {
        // Validando que el textView inicie en 1
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))

        // Hacemos clic en el boton ibSum
        onView(withId(R.id.ibSum))
            .perform(click())

        // validando que el numero sume 1 (2 en total)
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("2")))
    }


    @Test
    fun setNewQuantity_sumLimit_noIncreasesTextField() {
        val scenario = activityRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)
        // limitando la cantidad en la actividad
        scenario.onActivity { activity ->
            activity.selectedProduct.quantity = 1
        }

        // Validando que el textView inicie en 1
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))

        // Hacemos clic en el boton ibSum
        onView(withId(R.id.ibSum))
            .perform(click())

        // validando que el numero no haya pasado del limite en este caso 1
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
    }

    @Test
    fun setNewQuantity_sub_reducesTestField() {
        // Validando que el textView inicie en 1
        onView(withId(R.id.etNewQuantity))
            .perform(ViewActions.replaceText("11"))

        // Hacemos clic en el boton ibSum
        onView(withId(R.id.ibSub))
            .perform(click())

        // validando que el numero no haya pasado del limite en este caso 1
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("10")))
    }

    @Test
    fun setNewQuantity_subLimit_noReducesTextField() {
        // Validando que el textView inicie en 1
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))

        // Hacemos clic en el boton ibSum
        onView(withId(R.id.ibSub))
            .perform(click())

        // validando que el numero no haya pasado del limite en este caso 1
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
    }

    @Test
    fun checkTextField_startQuantity(){
        onView(allOf(withId(R.id.etNewQuantity), withContentDescription(R.string.description_new_quantity))) // permite abarcar todas las coincidencias posibles
            .check(matches(withText("1")))

        onView(allOf(withId(R.id.etNewQuantity), not(withContentDescription(R.string.description_new_quantity_alt)))) // permite abarcar todas las coincidencias posibles
            .check(matches(withText("1")))

        onView(allOf(withId(R.id.etNewQuantity), withContentDescription(R.string.description_new_quantity_alt))) // permite abarcar todas las coincidencias posibles
            .check(matches(withText("5")))
    }

}
package com.cursosandroidant.inventory.mainModule.view.adapters

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.R
import com.cursosandroidant.inventory.clickInChild
import com.cursosandroidant.inventory.mainModule.view.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listItem_click_successCheck() {
        // Click sobre el segundo elemento
        Espresso.onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, click()))

        // Validar que el texto que se seleccionó sea "Queso"
        Espresso.onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Queso")))
    }

    @Test
    fun listItem_longClick_removeFromView() {
        onView(withId(R.id.recyclerView)).perform(
            actionOnItem<ProductAdapter.ViewHolder>(
                hasDescendant(withText(containsString("Tijeras"))), longClick()
            ),

            scrollTo<ProductAdapter.ViewHolder>(
                hasDescendant(withText(containsString("Globo")))
            )
        )

        try {
            onView(withId(R.id.recyclerView)).perform(
                scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Tijeras")))
                )
            )

            fail("Tijeras aún existe!") // Generamos error, indicando que no se ha eliminado
        } catch (e: Exception) {
            assertThat((e as? PerformException), `is`(notNullValue()))
        }
    }

    @Test
    fun cbFavorite_click_changeState() {
        onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<ProductAdapter.ViewHolder>(
                1,
                clickInChild(R.id.cbFavorite)
            )
        )
    }

}
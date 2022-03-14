package com.cursosandroidant.inventory.mainModule.view


import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun actionBar_menuItemClick_returnsMsg() {
        // Con esto intentamos cerrar algun menú contextual y evitamos dar click sobre
        // algún elemento no deseado
        onView(withId(R.id.recyclerView)).perform(click())

        onView(withId(R.id.action_history)).perform(click())

        // Extrar el contenido dinómico del texto
        var snackMsg = ""
        activityRule.scenario.onActivity { activity ->
            snackMsg = activity.resources.getString(R.string.main_msg_go_history)
        }
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackMsg)))
    }

    // Ingresar a un item oculto del menú
    @Test
    fun contextMenu_menuItemClick_returnsMsg(){
        // Aseguramos que la vista esta lista
        onView(withId(R.id.recyclerView)).perform(click())

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        // Extrar el contenido dinámico del texto
        var snackMsg = ""
        var snackMsgExit = ""

        activityRule.scenario.onActivity { activity ->
            snackMsg = activity.resources.getString(R.string.main_msg_go_exit)
            snackMsgExit = activity.resources.getString(R.string.main_menu_title_exit)
        }

        onView(withText(snackMsgExit)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackMsg)))
    }
}

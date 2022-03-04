package com.cursosandroidant.inventory.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkWelcomeTest(){
        // Contexto y aplicacion
        val mainVIewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}
        try {
            mainVIewModel.isWelcome().observeForever(observer)
            val result = mainVIewModel.isWelcome().value
            assertThat(result, Matchers.not(nullValue()))
            assertThat(result, `is`(true))
        } finally {
            mainVIewModel.isWelcome().removeObserver(observer)
        }

    }
}
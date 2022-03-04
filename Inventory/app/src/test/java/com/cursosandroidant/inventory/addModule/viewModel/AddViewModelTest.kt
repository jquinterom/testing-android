package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest(){
        val addViewModel = AddViewModel()
        val product = Product(117, "Xbox", 20, "https://www.google.com" +
                "/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fc%2Fc2%2FXbox-Console-Set.png&imgrefurl=https%3A%2F%2Fes.wikipedia.org%2Fwiki%2FXbox&tbnid=UMKcphYXL8v0uM&vet=12ahUKEwj5g7-b4qz2AhXjszEKHZdtC2AQMygAegUIARDbAQ..i&docid=hDQ83N-n_QWbSM&w" +
                "=4800&h=2200&q=xbox&client=ubuntu&ved=2ahUKEwj5g7-b4qz2AhXjszEKHZdtC2AQMygAegUIARDbAQ",
            4.6, 45)

        val observer = Observer<Boolean>{}
        try {
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value
            assertThat(result, `is`(true))
            // assertThat(result, not(nullValue())) // Para válidar que el valor no sea null
        } finally {
            // Liberar el observador
            addViewModel.getResult().removeObserver(observer)
        }
    }

}
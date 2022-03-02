package com.cursosandroidant.calculator

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculatorUtilsMockTest {
    @Mock
    lateinit var operations: Operations

    @Mock
    lateinit var listener: OnResolveListener

    lateinit var calculatorUtils: CalculatorUtils

    @Before
    fun setup(){
        calculatorUtils = CalculatorUtils(operations, listener)
    }

    @Test
    fun calc_callCheckOrResolve_noReturn(){
        val operation = "-5*2.5"
        val isFromResolve = true
        calculatorUtils.checkOrResolve(operation, isFromResolve)
        verify(operations).tryResolve(operation, isFromResolve, listener)
    }

    @Test
    fun calc_callAddOperator_validSub_noReturn(){
        val operator = "-"
        val operation = "4+" // 4+-3
        var isCorrect = false
        calculatorUtils.addOperator(operator, operation){
            isCorrect = true
        }

        assertTrue(isCorrect)
    }

    @Test
    fun calc_callAddOperator_invalidSub_noReturn(){
        val operator = "-"
        val operation = "4+."
        var isCorrect = false
        calculatorUtils.addOperator(operator, operation){
            isCorrect = true
        }
        assertFalse(isCorrect)
    }

    @Test
    fun calc_callAddPoint_firstPoint_noReturn(){
        val operation = "3x2"
        var isCorrect = false
        calculatorUtils.addPoint(operation){
            isCorrect = true
        }
        assertTrue(isCorrect)
        verifyNoInteractions(operations)
    }

    @Test
    fun calc_callAddPoint_secondPoint_noReturn(){
        val operation = "3.4x2"
        val operator = "x"
        var isCorrect = false

        // Simulando respuesta a multiplicación = x
        `when`(operations.getOperator(operation)).thenReturn("x")
        // Simulando respuesta
        `when`(operations.divideOperation(operator, operation)).thenReturn(arrayOf("3.5", "2"))

        calculatorUtils.addPoint(operation){
            isCorrect = true
        }
        assertTrue(isCorrect)
        verify(operations).getOperator(operation)
        verify(operations).divideOperation(operator, operation)
    }
}
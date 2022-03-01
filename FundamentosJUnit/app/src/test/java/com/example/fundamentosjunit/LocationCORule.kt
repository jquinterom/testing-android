package com.example.fundamentosjunit

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class LocationCORule : TestRule {
    var assertions: Assertions? = null

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                assertions = Assertions()
                assertions?.setLocation("CO")
                try {
                    base?.evaluate() // Ejecutar la prueba actual
                } finally {
                    assertions = null
                }
            }
        }
    }
}
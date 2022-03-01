package com.cursosandroidant.auth

import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.Assert.assertTrue

class AuthTest {
    // accion _ en base a que escenario _ que es lo que debe pasar
    @Test
    fun login_complete_returnsTrue(){
        val isAuthenticated = userAuthentication("ant@gmail.com", "1234")
        assertTrue(isAuthenticated)
    }

    // accion _ en base a que escenario _ que es lo que debe pasar
    @Test
    fun login_complete_returnsFalse(){
        val isAuthenticated = userAuthentication("ant@gmail.com", "12345")
        assertFalse(isAuthenticated)
    }


    @Test
    fun login_emptyEmail_returnsFalse(){
        val isAuthenticated = userAuthentication("", "12345")
        assertFalse(isAuthenticated)
    }



    /* -- TDD
    @Test
    fun login_nullEmail_returnsFalse(){
        val isAuthenticated = userAuthenticationTDD(null, "12345")
        assertFalse(isAuthenticated)
    }

    @Test
    fun login_nullPassword_returnsFalse(){
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", null)
        assertFalse(isAuthenticated)
    }
    */


}
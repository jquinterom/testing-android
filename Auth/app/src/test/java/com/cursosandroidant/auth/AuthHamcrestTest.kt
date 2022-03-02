package com.cursosandroidant.auth

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.lang.Exception

class AuthHamcrestTest {

    // given-when-then

    @Test
    fun loginUser_correctData_returnsSuccessEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "1234")
        assertThat(AuthEvent.USER_EXISTS, `is`(result))
    }

    @Test
    fun loginUser_wrongData_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("nt@gmail.com", "1234")
        Assert.assertEquals(AuthEvent.NOT_USER_EXISTS, isAuthenticated)
    }

    @Test
    fun loginUser_emptyEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "1234")
        Assert.assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }

    @Test
    fun loginUser_emptyPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "")
        Assert.assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "")
        Assert.assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }

    @Test
    fun loginUser_invalidEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmailcom", "1234")
        Assert.assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "123e")
        Assert.assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }

    @Test
    fun loginUser_invalidUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmailcom", "123e")
        Assert.assertEquals(AuthEvent.INVALID_USER, isAuthenticated)
    }

    // region Prueba con excepciones con clase personalizada
    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsException() {
        val isAuthenticated = userAuthenticationTDD(null, "123e")
        Assert.assertEquals(AuthEvent.NULL_EMAIL, isAuthenticated)
    }

    @Test
    fun loginUser_nullPassword_returnsException(){
        Assert.assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("ant@gmailcom", null))
        }
    }

    @Test
    fun loginUser_nullForm_returnsException(){
        try{
            val result = userAuthenticationTDD(null, null)
            Assert.assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }
    // endregion

    @Ignore("Falta definir un requisito del cliente")
    @Test
    fun loginUser_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "12345")
        Assert.assertEquals(AuthEvent.PASSWORD_INVALID_LENGTH, isAuthenticated)
    }
}
package com.cursosandroidant.auth

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.lang.Exception

class AuthAlTest {
    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "1234")
        Assert.assertEquals(AuthEvent.USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("nt@gmail.com", "1234")
        Assert.assertEquals(AuthEvent.NOT_USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "1234")
        Assert.assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "")
        Assert.assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("", "")
        Assert.assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmailcom", "1234")
        Assert.assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "123e")
        Assert.assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD("ant@gmailcom", "123e")
        Assert.assertEquals(AuthEvent.INVALID_USER, isAuthenticated)
    }

    // region Prueba con excepciones con clase personalizada
    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException() {
        val isAuthenticated = userAuthenticationTDD(null, "123e")
        Assert.assertEquals(AuthEvent.NULL_EMAIL, isAuthenticated)
    }

    @Test
    fun login_nullPassword_returnsException(){
        Assert.assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("ant@gmailcom", null))
        }
    }

    @Test
    fun login_nullForm_returnsException(){
        try{
            val result = userAuthenticationTDD(null, null)
            Assert.assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Ignore("Falta definir un requisito del cliente")
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("ant@gmail.com", "12345")
        Assert.assertEquals(AuthEvent.PASSWORD_INVALID_LENGTH, isAuthenticated)
    }
    // endregion
}
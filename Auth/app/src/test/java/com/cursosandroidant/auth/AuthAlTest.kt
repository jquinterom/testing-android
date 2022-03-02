package com.cursosandroidant.auth

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class AuthAlTest {
    private var email: String? = null
    private var password: String? = null

    @Before
    fun setup(){
        email = "ant@gmail.com"
        password = "1234"
    }

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.NOT_USER_EXISTS, isAuthenticated)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent() {
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.INVALID_USER, isAuthenticated)
    }

    // region Prueba con excepciones con clase personalizada
    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException() {
        try{
            val result = userAuthenticationTDD(email, password)
            Assert.assertEquals(AuthEvent.NULL_EMAIL, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_EMAIL, it.authEvent)
            }
        }
    }

    @Test
    fun login_nullPassword_returnsException(){
        try{
            val result = userAuthenticationTDD(email, password)
            Assert.assertEquals(AuthEvent.NULL_PASSWORD, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_PASSWORD, it.authEvent)
            }
        }
    }

    @Test
    fun login_nullForm_returnsException(){
        try{
            val result = userAuthenticationTDD(email, password)
            Assert.assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception){
            (e as? AuthException)?.let {
                Assert.assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }
    // endregion

    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email, password)
        Assert.assertEquals(AuthEvent.PASSWORD_INVALID_LENGTH, isAuthenticated)
    }
}
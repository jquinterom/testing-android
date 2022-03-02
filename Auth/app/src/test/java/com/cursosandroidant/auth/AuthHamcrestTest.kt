package com.cursosandroidant.auth

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert
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
        val result = userAuthenticationTDD("nt@gmail.com", "1234")
        assertThat(AuthEvent.NOT_USER_EXISTS, `is`(result))
    }

    @Test
    fun loginUser_emptyEmail_returnsFailEvent() {
        val result = userAuthenticationTDD("", "1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_emptyPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "")
        assertThat(AuthEvent.EMPTY_PASSWORD, `is`(result))
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent() {
        val result = userAuthenticationTDD("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is`(result))
    }

    @Test
    fun loginUser_invalidEmail_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmailcom", "1234")
        assertThat(AuthEvent.INVALID_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmail.com", "123e")
        assertThat(AuthEvent.INVALID_PASSWORD, `is`(result))
    }

    @Test
    fun loginUser_invalidUser_returnsFailEvent() {
        val result = userAuthenticationTDD("ant@gmailcom", "123e")
        assertThat(AuthEvent.INVALID_USER, `is`(result))
    }

    // region Prueba con excepciones con clase personalizada
    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsException() {
        val result = userAuthenticationTDD(null, "123e")
        assertThat(AuthEvent.NULL_EMAIL, `is`(result))
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
            assertThat(AuthEvent.NULL_FORM, `is`(result))
        } catch (e: Exception){
            (e as? AuthException)?.let {
                assertThat(AuthEvent.NULL_FORM,`is`(it.authEvent))
            }
        }
    }
    // endregion

    @Test
    fun loginUser_errorLengthPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("ant@gmail.com", "12345")
        assertThat(AuthEvent.PASSWORD_INVALID_LENGTH, `is`(result))
    }

    @Test
    fun checkNames_differentUsers_match(){
        assertThat("Linda", both(containsString("a")).and(containsString("i")))
    }

    @Test
    fun checkData_emailPassword_noMatch(){
        val email = "ant@gmail.com"
        val password = "1234"
        assertThat(email, not(`is`(password)))
    }

    @Test
    fun checkExists_newEmail_returnsString(){
        val oldEmail = "ant@gmail.com"
        val newEmail = "new@gmail.com"
        val emails = arrayOf(oldEmail, newEmail)
        assertThat(emails, hasItemInArray(newEmail))
    }

    @Test
    fun checkDomain_arrayEmails_returnsString(){
        val oldEmail = "ant@hotmail.com"
        val newEmail = "new@gmail.com"
        val nextEmail = "other@gmail.com"
        val emails = arrayListOf(oldEmail, newEmail, nextEmail)
        val newEmails = arrayListOf(newEmail, nextEmail)
        assertThat(newEmails, everyItem(endsWith("gmail.com")))
    }
}
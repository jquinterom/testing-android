package com.cursosandroidant.auth

enum class AuthEvent {
    // Success
    USER_EXISTS,
    //Fail
    NOT_USER_EXISTS,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    EMPTY_FORM,
    INVALID_EMAIL,
    INVALID_PASSWORD,

}
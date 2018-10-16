package com.technoidentity.procm.api.response.login

/**
 * Login Response from API
 */
data class LoginResponse(
    val token: String?,
    val user: User
)
package com.technoidentity.procm.api.response.login

/**
 * User Profile from API
 */
data class User(
    val email: String?,
    val firstName: String?,
    val gender: String?,
    val id: String?,
    val lastName: String?
)
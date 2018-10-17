package com.technoidentity.procm.repository

import com.technoidentity.procm.api.ApiService
import com.technoidentity.procm.api.request.login.LoginRequest
import com.technoidentity.procm.api.response.login.LoginResponse
import io.reactivex.Single
import javax.inject.Singleton

/**
 * Login Repository
 *
 * @param apiService
 */
@Singleton
class LoginRepository(val apiService: ApiService) {

  fun createLogin(loginRequest: LoginRequest): Single<LoginResponse> {

    return apiService.postLogin(loginRequest)
  }
}
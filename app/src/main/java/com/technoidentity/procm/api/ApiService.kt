package com.technoidentity.procm.api

import android.arch.lifecycle.LiveData
import com.technoidentity.procm.api.request.login.LoginRequest
import com.technoidentity.procm.api.response.login.LoginResponse
import com.technoidentity.procm.database.entity.Task
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * All API Endpoints are redirected from here
 */
interface ApiService {

  @POST("/auth/login")
  fun postLogin(@Body loginRequest: LoginRequest): Single<LoginResponse>

  @GET("/task/all")
  fun getTaskList(): LiveData<ApiResponse<List<Task>>>
}
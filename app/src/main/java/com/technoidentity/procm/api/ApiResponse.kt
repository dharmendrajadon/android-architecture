package com.technoidentity.procm.api

import okhttp3.Headers
import retrofit2.Response

/**
 * Common class used by API responses.
 * @param <T> the type of the response object
 */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {

  companion object {

    fun <T> create(error: Throwable): ApiErrorResponse<T> {
      return ApiErrorResponse(error.message ?: "unknown error")
    }

    fun <T> create(response: Response<T>): ApiResponse<T> {

      // Check if request is successful
      return if (response.isSuccessful) {
        val body = response.body()

        // Response has empty body
        if (body == null || response.code() == 204) {
          ApiEmptyResponse()
        } else {
          ApiSuccessResponse(body, response.headers())
        }
      } else {
        val msg = response.errorBody()?.string()
        val errorMsg = if (msg.isNullOrEmpty()) {
          response.message()
        } else {
          msg
        }
        ApiErrorResponse(errorMsg ?: "unknown error")
      }
    }
  }
}

/**
 * separate class for HTTP 200..299(Not 204) responses.
 */
data class ApiSuccessResponse<T>(val body: T, val headers: Headers) : ApiResponse<T>()

/**
 * separate class for HTTP 204 responses for making response body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

/**
 * separate class for HTTP Errors
 */
data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
package com.technoidentity.procm.dagger.modules

import com.technoidentity.procm.BuildConfig
import com.technoidentity.procm.api.ApiService
import com.technoidentity.procm.utils.AppPrefs
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Network Module for providing current context requirements
 */
@Module
class NetworkModule {

  /**
   * OkHttp3 Client for Retrofit Requests
   * It need Auth Token from App Preferences for API Authentication
   * @param appPrefs App Preference Object
   * @return {OkHttpClient} Returns OkHttp3 Client
   */
  @Provides
  @Singleton
  internal fun providesOkhttpClient(appPrefs: AppPrefs): OkHttpClient {

    // Create okHTTP3 Client
    val httpClient = OkHttpClient.Builder()

    // For Debug HTTP Logging
    if (BuildConfig.DEBUG) {

      // Create HTTP Interceptor
      val httpInterceptor = HttpLoggingInterceptor()
      httpInterceptor.level = HttpLoggingInterceptor.Level.BODY

      // Add Interceptor to Http Client
      httpClient.addInterceptor(httpInterceptor)
    }

    // Read TimeOut
    httpClient.readTimeout(60, TimeUnit.SECONDS)

    // Connect TimeOut
    httpClient.connectTimeout(60, TimeUnit.SECONDS)

    // Write TimeOut
    httpClient.writeTimeout(60, TimeUnit.SECONDS)

    // Attach Auth Token to every request for API Authentication
    httpClient.addInterceptor { chain ->

      // Get Original Request
      val originalRequest = chain.request()

      // Update the request with Bearer Token Header
      val updatedRequest = originalRequest.newBuilder()
          .header("Authorization", "Bearer ${appPrefs.userToken}")
          .method(originalRequest.method(), originalRequest.body())
          .build()

      // Proceed request with updated Auth header
      chain.proceed(updatedRequest)
    }

    // Return HTTP Client
    return httpClient.build()
  }

  /**
   * Retrofit object for API integration and handling
   * It need okHttp3 client for making http requests
   * @param httpClient OkHttp3 Client Object
   * @return {Retrofit} Returns Retrofit Object
   */
  @Provides
  @Singleton
  internal fun providesRetrofit(httpClient: OkHttpClient): Retrofit {

    // API Base URL
    // Converter Factory
    // Retrofit Call to RxJava Adapter
    return Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build()
  }

  /**
   * API service Injection
   * It need retrofit object
   * @param retrofit Retrofit Object
   * @return {ApiService} Returns ApiService Object
   */
  @Provides
  @Singleton
  internal fun providesApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }


}
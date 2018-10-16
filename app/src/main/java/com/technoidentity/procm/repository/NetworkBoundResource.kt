package com.technoidentity.procm.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.technoidentity.procm.api.ApiEmptyResponse
import com.technoidentity.procm.api.ApiErrorResponse
import com.technoidentity.procm.api.ApiResponse
import com.technoidentity.procm.api.ApiSuccessResponse

/**
 * A generic class that can provide a resource backed by both the sq-lite database and the network.
 *
 * @param <ResultType>
 * @param <RequestType>
 */
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread
constructor() {

  private val result = MediatorLiveData<Resource<ResultType>>()

  // Returns a liveData that represents the resource
  val asLiveData: LiveData<Resource<ResultType>>
    get() = result

  init {

    // Set resource loading
    result.value = Resource.loading(null)

    @Suppress("LeakingThis")
    val dbSource = loadFromDb()

    // Add source of data to result
    result.addSource(dbSource) { data ->

      // Unhook Database Source
      result.removeSource(dbSource)

      // If need to fetch data from API
      if (shouldFetch(data)) {
        fetchFromNetwork(dbSource)
      } else {
        result.addSource(dbSource) { newData ->
          setValue(Resource.success(newData))
        }
      }

    }
  }

  /**
   * For fetching data from API
   * @param dbSource
   */
  private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

    // Create API Call
    val apiResponse = createCall()

    // Re-Attach dbSource as new source,
    // it will dispatch its latest value quickly
    result.addSource(dbSource) { newData ->
      setValue(Resource.loading(newData))
    }

    // For populating API response
    result.addSource(apiResponse) { response ->

      // Unhook Api Call
      result.removeSource(apiResponse)

      // Unhook Database Source
      result.removeSource(dbSource)

      // If response is successful
      when (response) {
        is ApiSuccessResponse -> {

          // Save API response data
          saveFetchData(response.body)

          // Update liveData source
          val loaded = loadFromDb()
          result.addSource(loaded) { newData ->
            result.removeSource(loaded)
            setValue(Resource.success(newData))
          }
        }
        is ApiEmptyResponse -> {

          // Reload from liveData source
          val loaded = loadFromDb()
          result.addSource(loaded) { newData ->
            result.removeSource(loaded)
            setValue(Resource.success(newData))
          }
        }
        is ApiErrorResponse -> {

          // On API error
          onFetchFailed()

          // Update liveData source
          result.addSource(dbSource) { newData ->
            setValue(Resource.error(response.errorMessage, newData))
          }
        }
      }

    }

  }

  @MainThread
  private fun setValue(newValue: Resource<ResultType>) {
    if (result.value != newValue) {
      result.value = newValue
    }
  }

  // Called to save the result of the API response into the database
  @WorkerThread
  protected abstract fun saveFetchData(item: RequestType)

  // Called with the data in the database to decide whether it should be
  // fetched from the network.
  @MainThread
  protected abstract fun shouldFetch(data: ResultType?): Boolean

  // Called to get the cached data from the database
  @MainThread
  protected abstract fun loadFromDb(): LiveData<ResultType>

  // Called to create the API call.
  @MainThread
  protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

  // Called when the fetch fails. The child class may want to reset components
  // like rate limiter.
  @MainThread
  protected abstract fun onFetchFailed()
}
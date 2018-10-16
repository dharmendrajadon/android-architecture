package com.technoidentity.procm.repository

import android.arch.lifecycle.LiveData
import com.technoidentity.procm.api.ApiResponse
import com.technoidentity.procm.api.ApiService
import com.technoidentity.procm.database.dao.TaskDao
import com.technoidentity.procm.database.entity.Task
import javax.inject.Singleton

/**
 * Task Repository
 *
 * @param taskDao
 * @param apiService
 */
@Singleton
class TaskRepository(val taskDao: TaskDao, val apiService: ApiService) {

  fun getTaskList(page: Int): LiveData<Resource<List<Task>>> {

    return object : NetworkBoundResource<List<Task>, List<Task>>() {

      override fun saveFetchData(item: List<Task>) {

        // Insert into database
        taskDao.insertAll(*item.toTypedArray())
      }

      override fun shouldFetch(data: List<Task>?): Boolean {
        return data == null || data.isEmpty()
      }

      override fun loadFromDb(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
      }

      override fun createCall(): LiveData<ApiResponse<List<Task>>> {
        return apiService.getTaskList()
      }

      override fun onFetchFailed() {

      }
    }.asLiveData
  }
}
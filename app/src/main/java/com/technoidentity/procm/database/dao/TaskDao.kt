package com.technoidentity.procm.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.technoidentity.procm.database.entity.Task
import com.technoidentity.procm.utils.Constants

/**
 * Interface for database access for Task related operations.
 */
@Dao
interface TaskDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(task: Task)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(vararg task: Task)

  @Query("SELECT * FROM ${Constants.TABLE_TASKS}")
  fun getAllTasks(): LiveData<List<Task>>

  @Query("DELETE FROM ${Constants.TABLE_TASKS}")
  fun deleteAll()

}
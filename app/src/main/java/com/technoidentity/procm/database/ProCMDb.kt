package com.technoidentity.procm.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.technoidentity.procm.database.dao.TaskDao
import com.technoidentity.procm.database.entity.Task

/**
 * Interface for database access for Task related operations.
 */
@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class ProCMDb : RoomDatabase() {

  abstract fun taskDao(): TaskDao
}

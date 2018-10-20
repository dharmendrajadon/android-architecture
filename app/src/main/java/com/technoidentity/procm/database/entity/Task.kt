package com.technoidentity.procm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.technoidentity.procm.utils.Constants

/**
 * Task Model for database.
 */
@Entity(tableName = Constants.TABLE_TASKS)
data class Task(
    @PrimaryKey
    @ColumnInfo(name = "taskId")
    val taskId: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "taskType")
    val taskType: String?
)
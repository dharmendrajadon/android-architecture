package com.technoidentity.procm.api.response.task

/**
 * Task Response from API
 */
data class TaskResponse(
    val taskId: String,
    val name: String?,
    val description: String?,
    val taskType: String?
)
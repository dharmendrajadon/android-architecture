@file:Suppress("DEPRECATION")

package com.technoidentity.procm.ui

import android.app.ProgressDialog
import android.content.Context

/**
 * Custom Loading Dialog UI Blocker
 */
class LoadingDialog constructor(context: Context?) : ProgressDialog(context) {

  // Custom Message, if any
  private var customMessage: String? = null

  // For Passing Custom Message
  constructor(context: Context?, message: String? = "Loading...") : this(context) {
    this.customMessage = message
  }

  // Default Configurations
  init {
    this.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER)
    this.setMessage(customMessage)
    this.isIndeterminate = true
    this.setCancelable(false)
    this.setCanceledOnTouchOutside(false)
  }
}
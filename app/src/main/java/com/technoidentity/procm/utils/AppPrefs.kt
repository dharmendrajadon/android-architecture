package com.technoidentity.procm.utils

import android.content.Context
import com.technoidentity.procm.utils.Constants
import net.grandcentrix.tray.AppPreferences
import javax.inject.Singleton

/**
 * Shared preference manager for local user credentials
 */
@Singleton
class AppPrefs(val context: Context) {

  private val appPreferences: AppPreferences = AppPreferences(context)

  // Discard return value Hack
  @Suppress("unused")
  private fun Any?.discard() = Unit

  // User Token
  var userToken
    get() = appPreferences.getString(Constants.SESSION_USER_TOKEN, "")
    set(token) = appPreferences.put(Constants.SESSION_USER_TOKEN, token).discard()

}
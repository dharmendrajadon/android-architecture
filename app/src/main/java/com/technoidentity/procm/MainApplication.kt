package com.technoidentity.procm

import com.google.firebase.FirebaseApp
import com.technoidentity.procm.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Main application class for base configuration
 * Here we extends DaggerApplication
 */
class MainApplication : DaggerApplication() {

  /**
   * Injecting Firebase in Application onCreate()
   */
  override fun onCreate() {
    FirebaseApp.initializeApp(this)
    super.onCreate()
  }

  /**
   * Inject Dagger App Component after onCreate()
   */
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().application(this).build()
  }

}
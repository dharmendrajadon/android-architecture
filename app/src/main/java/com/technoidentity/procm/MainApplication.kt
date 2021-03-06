package com.technoidentity.procm

import com.google.firebase.FirebaseApp
import com.technoidentity.procm.dagger.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

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

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  /**
   * Inject Dagger App Component after onCreate()
   */
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().application(this).build()
  }

}
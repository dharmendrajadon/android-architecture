package com.technoidentity.procm.dagger.modules

import android.app.Application
import android.content.Context
import com.technoidentity.procm.dagger.ViewModelModule
import com.technoidentity.procm.utils.AppPrefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * App Module to provide App-Level Dependencies
 */
@Module(includes = [
  ViewModelModule::class,
  NetworkModule::class,
  DatabaseModule::class
])
internal class AppModule {

  @Provides
  @Singleton
  fun providesContext(application: Application): Context {
    return application
  }

  @Provides
  @Singleton
  fun providesAppPrefs(context: Context): AppPrefs {
    return AppPrefs(context)
  }

}
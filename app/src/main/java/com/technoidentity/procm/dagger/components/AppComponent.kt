package com.technoidentity.procm.dagger.components

import android.app.Application
import com.technoidentity.procm.MainApplication
import com.technoidentity.procm.dagger.modules.ActivityContributorModule
import com.technoidentity.procm.dagger.modules.AppModule
import com.technoidentity.procm.dagger.modules.DatabaseModule
import com.technoidentity.procm.dagger.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * App Component for Building DaggerAppComponent
 */
@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppModule::class,
  ActivityContributorModule::class,
  NetworkModule::class,
  DatabaseModule::class
])
interface AppComponent : AndroidInjector<MainApplication> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent

  }

}